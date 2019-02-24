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
import java.sql.SQLException;

@Controller
@RequestMapping ("/admin/order/")
public class AdminOrderUpdate {

    @Autowired
    private Connection connection;

    @GetMapping ("/cansel/{id}")
    @ResponseBody
    public String canselOrder (@PathVariable String id) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.orders SET \"status\"=-1 WHERE id=?");
            statement.setInt(1,Integer.valueOf(id));
            int counter = statement.executeUpdate();
            if (counter==0) {
                return "false";
            }
            return "true";
        } catch (SQLException e) {
            return "false";
        }
    }


    @GetMapping ("/confirm/{id}")
    @ResponseBody
    public String confirmOrder (@PathVariable String id) {
        //todo не сохраняются изменения, БАГ!!!!!
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE public.orders SET \"status\"=2 WHERE id=?");
            statement.setInt(1,Integer.valueOf(id));
            int counter = statement.executeUpdate();
            if (counter==0) {
                return "false";
            }
            return "true";
        } catch (SQLException e) {
            return "false";
        }
    }

    @GetMapping ("/additem")
    public String changedOrder (Model model, HttpServletRequest request) {
        Order adminOrder = (Order) request.getSession().getAttribute("AdminOrder");
        model.addAttribute("SelectedOrder",adminOrder);
        return "adminorderdetails";
    }

    @PostMapping("/additem")
    @ResponseBody
    public String changeOrder (HttpServletRequest request,  @RequestBody OrderItem item) {
        Order adminOrder = (Order) request.getSession().getAttribute("AdminOrder");
        boolean added = adminOrder.addOrderItem(item);
        return added?"success":"false";
    }
    @DeleteMapping("/deleteitem")
    @ResponseBody
    public String deleteItemFromOrder (HttpServletRequest request,  @RequestBody OrderItem item, Model model) {

        Order adminOrder = (Order) request.getSession().getAttribute("AdminOrder");

        boolean deleted = adminOrder.removeOrderItem(item);
        return deleted?"success":"false";
    }


}
