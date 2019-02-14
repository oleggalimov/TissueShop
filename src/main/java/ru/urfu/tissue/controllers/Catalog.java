package ru.urfu.tissue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.urfu.tissue.dao.Tissue;
import ru.urfu.tissue.utils.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class Catalog {
    @Autowired
    ConnectionCreator connectionCreator;
    private String SELECT_ALL = "SELECT * FROM public.Tissues";

    @RequestMapping("/catalog")
    public String list_all(Model model)  {

        try (Connection connection = connectionCreator.createConnection()) {

            ResultSet resultSet = connection.prepareStatement(SELECT_ALL).executeQuery();
            ArrayList <Tissue> result = new ArrayList<>();
            while (resultSet.next()) {
                Tissue temp = new Tissue();
                temp.setId(resultSet.getInt(1));
                temp.setName(resultSet.getString(2));
                temp.setPrice(resultSet.getFloat(3));
                result.add(temp);
            }
            model.addAttribute("tissues_list", result);
            return "catalog";
        } catch (SQLException e) {
            System.out.println("Ошибка получения каталога");
            model.addAttribute("Error_message", e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }
}
