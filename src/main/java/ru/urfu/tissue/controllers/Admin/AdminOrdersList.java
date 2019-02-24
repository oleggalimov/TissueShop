package ru.urfu.tissue.controllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.urfu.tissue.dao.Order;
import ru.urfu.tissue.utils.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

@Controller
@RequestMapping ("/admin/orderslist")
public class AdminOrdersList {

    @Autowired
    private Connection conn;

    @GetMapping
    public String returnAdmOrdersList(Model model) {
        try {
            ResultSet resultSet = conn.prepareStatement("SELECT id, first_name, last_name, phone_number,address, status, creation_date,sending_date,receving_date FROM public.orders").executeQuery();
            LinkedList<Order> orders = new LinkedList<>();
            while (resultSet.next()) {
                Order o = new Order(resultSet.getInt(1));
                o.setClientName(resultSet.getString(2));
                o.setClientLastName(resultSet.getString(3));
                o.setPhoneNumber(resultSet.getLong(4));
                o.setAddress(resultSet.getString(5));
                o.setStatus(resultSet.getInt(6));
                o.setCreationDate(resultSet.getDate(7)!=null? resultSet.getDate(7).getTime():null);
                o.setSendDate(resultSet.getDate(8)!=null? resultSet.getDate(8).getTime():null);
                o.setReceivedDate(resultSet.getDate(9)!=null? resultSet.getDate(9).getTime():null);
                orders.add(o);
            }
            model.addAttribute("Orders", orders);
            return "adminorderslist";
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("Error", e.getMessage());
            return "error";
        }
    }

}
