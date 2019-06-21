package com.xqz.seckill.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(String message){
        amqpTemplate.convertAndSend(MQConfig.QUEUE_NAME, message);
    }
}
