package com.xqz.seckill.controller;

import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserDAO userDAO;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("salt", "USZdRw");
        return "login";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@Valid User user, Model model){
        return "XXX";
    }
}
