package com.xqz.seckill.controller;

import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HelloController {
    @Autowired
    UserDAO userDAO;

    @GetMapping("/alluserss")
    public String getAllBooks(Model model){
        List<User> users = userDAO.findAll();
        if(users != null) {
            model.addAttribute("users", users);
        }
        return "hello";
    }
}
