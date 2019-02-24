package ru.urfu.tissue.controllers.Common;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;



@Controller
public class Index {
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        return "index";
    }
}
