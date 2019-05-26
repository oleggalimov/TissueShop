package ru.urfu.tissue.controllers.Common;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.urfu.tissue.aop.customannotations.RequestLogger;


import javax.servlet.http.HttpServletRequest;



@Controller
public class Index {
    @RequestLogger
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        return "index";
    }
}
