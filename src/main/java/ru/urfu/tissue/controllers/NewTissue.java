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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class NewTissue {
    @Autowired
    ConnectionCreator connectionCreator;
    private String SELECT_ALL = "INSERT INTO public.tissues (name, price, quantity) values (?,?,?)";
    @RequestMapping("/tissues/new")
    public String list_all(Model model)  {
        System.out.println(model.toString());
//
//        Connection connection = connectionCreator.createConnection();
//
//        try {
//            ResultSet resultSet = connection.prepareStatement(SELECT_ALL).executeQuery();
//            ArrayList <String []> result = new ArrayList<>();
//            while (resultSet.next()) {
//                String [] temp = new String[4];
//                temp[0]=String.valueOf(resultSet.getInt(1));
//                temp[1]=resultSet.getString(2);
//                temp[2]=String.valueOf(resultSet.getDouble(3));
//                temp[3]=String.valueOf(resultSet.getDouble(4));
//                result.add(temp);
//            }
//            model.addAttribute("tissues_list", result);
//            return "Tissues";
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return String.valueOf(e.getErrorCode());
//        }
//        model.addAttribute("tissue", new );
        return "tissue";
    }
}
