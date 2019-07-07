package com.xqz.seckill.controller;

import com.xqz.seckill.security.SecurityContextHelper;
import com.xqz.seckill.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/hello")
@Validated
public class HelloController {

    @Autowired
    SecurityContextHelper securityContextHelper;
    @Autowired
    WebSocketServer socket;

    @GetMapping("/ws")
    public String ws(){
        return "ws";
    }

    @GetMapping("/send")
    @ResponseBody
    public String send(String message) throws IOException {
        String username = securityContextHelper.getUser().getUsername();
        socket.send(username, message);
        return "SUCCESS";
    }

    @GetMapping("/bc")
    @ResponseBody
    public String bc(String message) throws IOException {
        socket.broadcast(message);
        return "SUCCESS";
    }
}
