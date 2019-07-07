package com.xqz.seckill.utils.mq;

import com.xqz.seckill.config.SeckillMQConfig;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.security.SecurityContextHelper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SeckillMQSender {

    @Autowired
    SecurityContextHelper securityContextHelper;
    @Autowired
    AmqpTemplate amqpTemplate;

    public void reduceStock(Long goodsId){
        amqpTemplate.convertAndSend(SeckillMQConfig.SECKILL_TOPIC,
                SeckillMQConfig.SECKILL_STOCK_ROUTINE_KEY, goodsId);
    }

    public void createSeckillOrder(User user, Long goodsId){
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        params.put("goodsId", goodsId);

        amqpTemplate.convertAndSend(SeckillMQConfig.SECKILL_TOPIC,
                SeckillMQConfig.SECKILL_ORDER_ROUTINE_KEY, params);
    }
}
