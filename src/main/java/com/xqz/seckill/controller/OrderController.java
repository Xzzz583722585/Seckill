package com.xqz.seckill.controller;

import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order_details")
    @ResponseBody
    public OrderInfo getAllSeckillGoods(Long orderId){
        return orderService.findOrderById(orderId);
    }
}
