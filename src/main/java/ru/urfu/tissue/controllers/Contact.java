package ru.urfu.tissue.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Contact {
    @RequestMapping (value = "/contact")
    public String contact() {
        return "contact";
    }
}
