package com.xqz.seckill.utils.mq;

import com.xqz.seckill.common.prefix.SeckillOrderPrefix;
import com.xqz.seckill.config.SeckillMQConfig;
import com.xqz.seckill.dao.SeckillGoodsDAO;
import com.xqz.seckill.dao.SeckillOrderInfoDAO;
import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.SeckillOrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.service.GoodsService;
import com.xqz.seckill.service.OrderService;
import com.xqz.seckill.utils.coder.JSONHelper;
import com.xqz.seckill.utils.redis.RedisService;
import com.xqz.seckill.utils.websocket.WebSocketServer;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

@Service
public class SeckillMQReceiver {

    @Autowired
    RedisService redis;
    @Autowired
    WebSocketServer socket;

    @Autowired
    OrderService orderService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    SeckillGoodsDAO seckillGoodsDAO;
    @Autowired
    SeckillOrderInfoDAO seckillOrderDAO;

    @RabbitListener(queues = SeckillMQConfig.SECKILL_STOCK_QUEUE)
    @Transactional
    public void reduceStock(Long goodsId){
        seckillGoodsDAO.reduceStock(1, goodsId);
    }

    @RabbitListener(queues = SeckillMQConfig.SECKILL_ORDER_QUEUE)
    @Transactional
    public void createSeckillOrder(Map<String, Object> params) throws IOException {
        User user = (User) params.get("user");
        Long goodsId = (Long) params.get("goodsId");

        GoodsVO goods = goodsService.findSeckillGoodsById(goodsId);

        OrderInfo order = orderService.createOrder(user, goods);

        SeckillOrderInfo seckillOrder = new SeckillOrderInfo();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setOrderId(order.getId());

        seckillOrderDAO.save(seckillOrder);
        redis.set(SeckillOrderPrefix.seckillOrderInfo, user.getId() + ":" + goods.getId(), seckillOrder);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socket.send(user.getUsername(), JSONHelper.toJSON(order.getId()));
    }
}
