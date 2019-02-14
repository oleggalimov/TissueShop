package ru.urfu.tissue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.urfu.tissue.dao.Order;
import ru.urfu.tissue.dao.OrderItem;
import ru.urfu.tissue.utils.ConnectionCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.HashSet;

@Controller
@RequestMapping(value = "/order")
//к классу применяется аспект checkOrder, который гарантированно создаст заказ

public class OrderController {
    @Autowired
    private
    ConnectionCreator connectionCreator;

    @GetMapping
    public String orderInfo(HttpServletRequest req, Model model) {
        HttpSession session = req.getSession(false);
        Order order = (Order) session.getAttribute("Order");
        model.addAttribute("Order", order);
        return "order";
    }

    @PostMapping
    public @ResponseBody
    String addItem(HttpServletRequest req, @RequestBody OrderItem item) {

        HttpSession session = req.getSession(false);
        Order order = (Order) session.getAttribute("Order");
        item.setTotalPrice(item.getPrice()*item.getQuantity());
        order.addOrderItem(item);
        session.setAttribute("Order", order);
        return "Added";
    }

    @PostMapping(value = "/send", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    public String sendOrder(
            HttpServletRequest req,
            Model model,
            @RequestParam(value = "clientName") String clientName,
            @RequestParam(value = "clientLastName") String clientLastName,
            @RequestParam(value = "phoneNumber") Long phoneNumber,
            @RequestParam(value = "address") String address
    ) throws SQLException {
        //валидируем длинну
        if (clientName.trim().length() == 0 || clientLastName.trim().length() == 0 || phoneNumber == null || address.trim().length() == 0) {
            System.out.println("Ошибка валидации формы");
            model.addAttribute("Error_message", "Отправленные данные некорректны!");
            return "error";
        } else {
            HttpSession session = req.getSession(false);
            Order order = (Order) session.getAttribute("Order");
            if (order.getItemsList().size()==0) {
                throw new NullPointerException("Корзина пуста!");
            }
            order.setClientName(clientName.trim());
            order.setClientLastName(clientLastName.trim());
            order.setPhoneNumber(phoneNumber);
            order.setAddress(address.trim());

            try (Connection connection = connectionCreator.createConnection()) {
                connection.setAutoCommit(false);

                String INSERT_ORDER = "INSERT INTO public.orders (first_name, last_name, phone_number,address,status,creation_date) values (?,?,?,?,1,?)";

                PreparedStatement saveorder = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                saveorder.setString(1,order.getClientName());
                saveorder.setString(2,order.getClientLastName());
                saveorder.setLong(3, order.getPhoneNumber());
                saveorder.setString(4, order.getAddress());
                saveorder.setTimestamp(5, new Timestamp(order.getCreationDate()));
                int i = saveorder.executeUpdate();
                if (i==0) {
                    throw new SQLException("No inserted values");
                } else {
                    int orderId=-1;
                    while (saveorder.getGeneratedKeys().next()) {
                        //переписать!
                        if(!saveorder.getGeneratedKeys().isLast()) {
                            throw new SQLException("Возвращено больше одного идентификатора");
                        }
                         orderId= saveorder.getGeneratedKeys().getInt(1);
                    }

                    String INSERT_ORDER_ITEMS = "INSERT INTO public.order_items (tissueId, name , price, quantity,total_price,order_id) values (?,?,?,?,?,?)";
                    PreparedStatement insertOrderItems = connection.prepareStatement(INSERT_ORDER_ITEMS);
                    HashSet itemsList = order.getItemsList();
                    for (Object item :itemsList) {
                        OrderItem orderItem = (OrderItem)item;
                        insertOrderItems.setInt(1,orderItem.getTissueId());
                        insertOrderItems.setString (2,orderItem.getName());
                        insertOrderItems.setFloat(3,orderItem.getPrice());
                        insertOrderItems.setFloat(4,orderItem.getQuantity());
                        insertOrderItems.setFloat(5,orderItem.getTotalPrice());
                        insertOrderItems.setFloat(6,orderId);
                        int insRowsCounter = insertOrderItems.executeUpdate();
                        if (insRowsCounter==0) {
                            throw new SQLException("Ошибка сохранения позиций");
                        }
                    }
                    order.setId(orderId);
                    order.setStatus(1);
                    connection.commit();
                }
            } catch (SQLException | NullPointerException e) {
                System.out.println("Ошибка сохранения заказа");
                model.addAttribute("Error_message", e.getCause());
                e.printStackTrace();
                return "error";
            }
            System.out.println(order);
            model.addAttribute("Order", order);
            session.setAttribute("Order", new Order());
            return "savedorder";
        }

    }
}
