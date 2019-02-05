package ru.urfu.tissue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.urfu.tissue.dao.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class Orderinfo {
    @RequestMapping(value = "/order")
    public String orderInfo(HttpServletRequest req, Model model)  {
        HttpSession session = req.getSession(true);
        Order order = (Order) session.getAttribute("Order");
        if (order==null) {
            System.out.println("Не найден заказ в текущей сессии");
            order=new Order();
            session.setAttribute("Order",order);
            model.addAttribute("Order", order);
        } else {
            model.addAttribute("Order", order);
        }
        return "order";
    }
}
