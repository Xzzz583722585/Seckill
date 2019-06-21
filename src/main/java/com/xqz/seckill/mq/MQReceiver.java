package com.xqz.seckill.mq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    @RabbitListener(queues = MQConfig.QUEUE_NAME)
    public void recive(String message){
        System.out.println(message);
    }
}
