package com.xqz.seckill.controller;

import com.xqz.seckill.domain.User;
import com.xqz.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("salt", "USZdRw");
        return "login";
    }

    @PostMapping("/register")
    @ResponseBody
    public Boolean register(@Valid User user){
        return userService.register(user);
    }
}
