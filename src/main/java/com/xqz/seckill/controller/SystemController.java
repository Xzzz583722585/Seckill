package com.xqz.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SystemController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("salt", "USZdRw");
        return "login";
    }
}
