package ru.urfu.tissue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.urfu.tissue.dao.Tissue;
import ru.urfu.tissue.utils.ConnectionCreator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class NewTissue {
    @Autowired
    ConnectionCreator connectionCreator;
    private String INSERT_VALUE = "INSERT INTO public.tissues (name, price, quantity) values (?,?,?)";
    @RequestMapping("/catalog/new")
    public String add_tissue (@ModelAttribute Tissue tissue, Model model)  {



        try (Connection connection = connectionCreator.createConnection()) {

            PreparedStatement statement = connection.prepareStatement(INSERT_VALUE);
            statement.setString(1,tissue.getName());
            statement.setFloat(2,tissue.getPrice());
            statement.execute();
            int updateCount = statement.getUpdateCount();
            if (updateCount==1) {
                return "catalog";
            } else {
                model.addAttribute("Error",updateCount);
                return "error";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("Error_message",e.getMessage());
            return "error";
        }

    }
}
