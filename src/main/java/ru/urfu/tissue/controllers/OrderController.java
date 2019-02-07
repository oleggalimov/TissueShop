package ru.urfu.tissue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.urfu.tissue.dao.Order;
import ru.urfu.tissue.dao.OrderItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
@RequestMapping(value = "/order")
public class OrderController {
    @GetMapping
    public String orderInfo(HttpServletRequest req, Model model)  {
        HttpSession session = req.getSession(true);
        Order order = (Order) session.getAttribute("Order");
        if (order==null) {
            System.out.println("Не найден заказ в текущей сессии");
            order=new ru.urfu.tissue.dao.Order();
            session.setAttribute("Order",order);
            model.addAttribute("Order", order);
        } else {
            model.addAttribute("Order", order);
        }
        return "order";
    }
    @PostMapping
    public @ResponseBody String addItem(HttpServletRequest req, @RequestBody OrderItem item) {

        HttpSession session = req.getSession(true);
        ru.urfu.tissue.dao.Order order = (Order) session.getAttribute("Order");
        if (order==null) {
            System.out.println("Не найден заказ в текущей сессии");
            order=new Order();
            order.addOrderItem(item);
            session.setAttribute("Order",order);
            return "No orders. Create new and add item";
        } else {
            order.addOrderItem(item);
            session.setAttribute("Order",order);
            return "Added";
        }
    }
}
