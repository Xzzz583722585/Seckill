package com.xqz.seckill.controller;

import com.xqz.seckill.common.result.ResultMsg;
import com.xqz.seckill.dao.UserDAO;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.mq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Email;
import java.util.List;

@Controller
@RequestMapping("/hello")
@Validated
public class HelloController {

    @Autowired
    MQSender mqSender;

    @GetMapping("/test")
    public String test(@RequestParam String message){
        mqSender.send(message);
        return "SUCCESS";
    }
}
