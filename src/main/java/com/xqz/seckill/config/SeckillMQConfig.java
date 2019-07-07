package com.xqz.seckill.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeckillMQConfig {

    public static final String SECKILL_TOPIC = "seckill";
    public static final String SECKILL_STOCK_QUEUE = "seckill_stock";
    public static final String SECKILL_ORDER_QUEUE = "seckill_order";
    public static final String SECKILL_STOCK_ROUTINE_KEY = "seckill.stock";
    public static final String SECKILL_ORDER_ROUTINE_KEY = "seckill.order";

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(SECKILL_TOPIC);
    }

    @Bean
    public Queue getSeckillStockQueue(){
        return new Queue(SECKILL_STOCK_QUEUE, true);
    }

    @Bean
    public Queue getSeckillOrderQueue(){
        return new Queue(SECKILL_ORDER_QUEUE, true);
    }

    @Bean
    public Binding getSeckillStockBinding(){
        return BindingBuilder.bind(getSeckillStockQueue()).to(getTopicExchange()).with(SECKILL_STOCK_ROUTINE_KEY);
    }

    @Bean
    public Binding getSeckillOrderBinding(){
        return BindingBuilder.bind(getSeckillOrderQueue()).to(getTopicExchange()).with(SECKILL_ORDER_ROUTINE_KEY);
    }
}
