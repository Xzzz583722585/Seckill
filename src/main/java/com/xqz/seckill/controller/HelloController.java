package com.xqz.seckill.controller;

import com.xqz.seckill.mq.SeckillMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
@Validated
public class HelloController {

    @Autowired
    SeckillMQSender seckillMqSender;

    @GetMapping("/test")
    @ResponseBody
    public String test(@RequestParam String message){
//        seckillMqSender.send(message);
        return "SUCCESS";
    }
}
