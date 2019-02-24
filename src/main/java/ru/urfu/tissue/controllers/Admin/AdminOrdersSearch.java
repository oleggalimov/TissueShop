package ru.urfu.tissue.controllers.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.urfu.tissue.dao.Order;
import ru.urfu.tissue.dao.OrderItem;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

@Controller
@RequestMapping("/admin/search")
public class AdminOrdersSearch {

    @Autowired
    private Connection connection;

    @GetMapping
    public String returnAdmOrdersList() {
        return "adminorderssearch";
    }

    @PostMapping
    public String searchOrder(@RequestParam Integer orderId, Model model, HttpServletRequest request) {
        Order adminOrder = (Order) request.getSession().getAttribute("AdminOrder");
        if (adminOrder!=null && adminOrder.getId().equals(orderId)) {

            model.addAttribute("SelectedOrder",adminOrder);
            return "adminorderdetails";
        }
        try {
                String selectOrders = "SELECT id,status,creation_date,sending_date,receving_date,first_name,last_name, phone_number, address FROM public.orders o WHERE o.id=? ";
                PreparedStatement statement = connection.prepareStatement(selectOrders);
                statement.setInt(1, orderId);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    throw new SQLException("Empty result!");
                }
                while (resultSet.next()) {
                    //переписать!
                    if (!resultSet.isLast()) {
                        throw new SQLException("Too many results");
                    } else {
                        //с временами беда
                        Order order = new Order(resultSet.getInt(1));
                        order.setStatus(resultSet.getInt(2));
                        if (resultSet.getDate(3) != null) {
                            order.setCreationDate(resultSet.getDate(3).getTime());
                        }
                        if (resultSet.getDate(4) != null) {
                            order.setSendDate(resultSet.getDate(4).getTime());
                        }
                        if (resultSet.getDate(5) != null) {
                            order.setReceivedDate(resultSet.getDate(5).getTime());
                        }
                        if (resultSet.getString(6) != null) {
                            order.setClientName(resultSet.getString(6));
                        }
                        if (resultSet.getString(7) != null) {
                            order.setClientLastName(resultSet.getString(7));
                        }
                        order.setPhoneNumber(resultSet.getLong(8));
                        if (resultSet.getString(9) != null) {
                            order.setAddress(resultSet.getString(9));
                        }
                        String orderItemsSelect = "SELECT tissueid,quantity,name,price,total_price FROM order_items WHERE order_id=?";
                        PreparedStatement orderItemsSelectQ = connection.prepareStatement(orderItemsSelect);
                        orderItemsSelectQ.setInt(1, order.getId());
                        ResultSet orderItemsRS = orderItemsSelectQ.executeQuery();
                        HashSet<OrderItem> items = new HashSet<>();
                        while (orderItemsRS.next()) {
                            OrderItem item = new OrderItem(
                                    orderItemsRS.getInt(1),
                                    orderItemsRS.getFloat(2),
                                    orderItemsRS.getString(3),
                                    orderItemsRS.getFloat(4),
                                    orderItemsRS.getFloat(5)
                            );
                            items.add(item);
                        }
                        order.setItemsList(items);
                        model.addAttribute("SelectedOrder", order);
                        request.getSession().setAttribute("AdminOrder", order);
                    }
                }
                return "adminorderdetails";
            } catch (SQLException | NullPointerException e) {
                model.addAttribute("ErrorMessage", "Ничего не найдено");
                e.printStackTrace();
                return "adminorderssearch";
            }
    }


}
