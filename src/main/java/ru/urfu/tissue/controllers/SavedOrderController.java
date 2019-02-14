package ru.urfu.tissue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.urfu.tissue.dao.Order;
import ru.urfu.tissue.dao.OrderItem;
import ru.urfu.tissue.utils.ConnectionCreator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

@Controller
@RequestMapping("/checkorder")
public class SavedOrderController {

    @Autowired
    private
    ConnectionCreator connectionCreator;

    @GetMapping
    public String checkSavedOrder(Model model) {
        return "savedorder";
    }

    @PostMapping(value = "/find", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public String findOrder(
            HttpServletRequest req,
            Model model,
            @RequestParam(value = "orderId") Integer orderId,
            @RequestParam(value = "phoneNumber") Long phoneNumber
    ) throws SQLException {
        if (orderId == 0 || phoneNumber == 0) {
            model.addAttribute("Error_message", "Отправленные данные некорректны!");
            return "error";
        } else {
            try (Connection conn =  connectionCreator.createConnection()) {
                String selectOrders = "SELECT id,status,creation_date,sending_date,receving_date,first_name,last_name, phone_number, address FROM public.orders o WHERE o.id=? AND o.phone_number=?";
                PreparedStatement statement = conn.prepareStatement(selectOrders);
                statement.setInt(1,  orderId );
                statement.setLong(2, phoneNumber);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("Результат поиска пуст");
                    return "redirect:/";
                }
                while (resultSet.next()) {
                    //переписать!
                    if(!resultSet.isLast()) {
                        throw new SQLException("Возвращено больше одного идентификатора");
                    } else {
                        //с временами беда

                        Order order = new Order(resultSet.getInt(1));
                        order.setStatus(resultSet.getInt(2));
                        if (resultSet.getDate(3)!=null) {
                            order.setCreationDate(resultSet.getDate(3).getTime());
                        }
                        if (resultSet.getDate(4)!=null) {
                            order.setSendDate(resultSet.getDate(4).getTime());
                        }
                        if (resultSet.getDate(5)!=null) {
                            order.setReceivedDate(resultSet.getDate(5).getTime());
                        }
                        if (resultSet.getString(6)!=null) {
                            order.setClientName(resultSet.getString(6));
                        }
                        if (resultSet.getString(7)!=null) {
                            order.setClientLastName(resultSet.getString(7));
                        }

                            order.setPhoneNumber(resultSet.getLong(8));
                        if (resultSet.getString(9)!=null) {
                            order.setAddress(resultSet.getString(9));
                        }
                        String orderItemsSelect = "SELECT tissueid,quantity,name,price,total_price FROM order_items WHERE order_id=?";
                        PreparedStatement orderItemsSelectQ=conn.prepareStatement(orderItemsSelect);
                        orderItemsSelectQ.setInt(1,order.getId());
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
                        model.addAttribute("SelectedOrder",order);
                    }
                }
            }catch (SQLException | NullPointerException e) {
                System.out.println("Ошибка поиска заказа");
                model.addAttribute("Error_message", e.getCause());
                e.printStackTrace();
                return "error";
            }
        }
        return "foundeddorder";
    }
}
