package com.xqz.seckill.controller;

import com.xqz.seckill.common.result.ResultMsg;
import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Email;
import java.util.List;

@Controller
@RequestMapping("/hello")
@Validated
public class HelloController {
    @Autowired
    UserDAO userDAO;

    @PostMapping("/allusers")
    public String getAllBooks(Model model){
        List<User> users = userDAO.findAll();
        if(users != null) {
            model.addAttribute("users", users);
        }
        return "hello";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResultMsg<String> test(@Email String email){
        ResultMsg<String> result = ResultMsg.build();
        result.setData(email);
        return result;
    }
}
