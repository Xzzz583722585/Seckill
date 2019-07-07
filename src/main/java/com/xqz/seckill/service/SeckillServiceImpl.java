package com.xqz.seckill.service;

import com.xqz.seckill.common.enums.OrderStatus;
import com.xqz.seckill.common.prefix.SeckillOrderPrefix;
import com.xqz.seckill.dao.SeckillOrderInfoDAO;
import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.SeckillOrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.utils.mq.SeckillMQSender;
import com.xqz.seckill.utils.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImpl implements SeckillService{

    @Autowired
    RedisService redis;
    @Autowired
    SeckillMQSender seckillMqSender;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillOrderInfoDAO seckillOrderDAO;

    @Override
    public SeckillOrderInfo findSeckillOrderByUserIdAndGoodsId(Long userId, Long goodsId) {
        SeckillOrderInfo orderInfo = redis.get(SeckillOrderPrefix.seckillOrderInfo,
                userId + ":" + goodsId, SeckillOrderInfo.class);
        if(orderInfo == null){
            orderInfo = seckillOrderDAO.findByUserIdAndGoodsId(userId, goodsId);
        }

        return orderInfo;
    }

    @Override
    public OrderInfo createSeckillOrder(User user, Long goodsId) {
        // 减库存
        seckillMqSender.reduceStock(goodsId);

        // 下订单
        seckillMqSender.createSeckillOrder(user, goodsId);

        OrderInfo order = new OrderInfo();
        order.setStatus(OrderStatus.QUEUING.getCode());
        return order;
    }
}
