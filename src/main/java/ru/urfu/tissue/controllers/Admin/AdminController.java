package ru.urfu.tissue.controllers.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.urfu.tissue.dao.Tissue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private Connection connection;

    @GetMapping
    public String controlPanel () {
        return "admin";
    }

    @GetMapping ("/catalog")
    public String secureCatalog (Model model) {
        try {

            String SELECT_ALL = "SELECT * FROM public.Tissues";
            ResultSet resultSet = connection.prepareStatement(SELECT_ALL).executeQuery();
            ArrayList<Tissue> result = new ArrayList<>();
            while (resultSet.next()) {
                Tissue temp = new Tissue();
                temp.setId(resultSet.getInt(1));
                temp.setName(resultSet.getString(2));
                temp.setPrice(resultSet.getFloat(3));
                result.add(temp);
            }
            model.addAttribute("tissues_list", result);
            return "admincatalog";
        } catch (SQLException e) {
            System.out.println("Ошибка получения каталога");
            model.addAttribute("Error_message", e.getMessage());
            e.printStackTrace();
            return "error";
        }

    }
}
