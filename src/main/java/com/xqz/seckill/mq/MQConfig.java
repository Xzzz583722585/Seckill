package com.xqz.seckill.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String QUEUE_NAME = "seckill";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME, true);
    }
}
