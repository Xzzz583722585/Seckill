package com.xqz.seckill.service;

import com.xqz.seckill.common.prefix.SeckillOrderPrefix;
import com.xqz.seckill.dao.SeckillGoodsDAO;
import com.xqz.seckill.dao.SeckillOrderInfoDAO;
import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.SeckillOrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.utils.redis.RedisService;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeckillServiceImpl implements SeckillService{

    @Autowired
    RedisService redis;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillOrderInfoDAO seckillOrderDAO;
    @Autowired
    SeckillGoodsDAO seckillGoodsDAO;

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
    @Transactional
    public OrderInfo createSeckillOrder(User user, GoodsVO goodsVO) {
        // 减库存
        seckillGoodsDAO.reduceStock(1, goodsVO.getId());

        // 下订单
        OrderInfo order = orderService.createOrder(user, goodsVO);

        SeckillOrderInfo seckillOrder = new SeckillOrderInfo();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setGoodsId(goodsVO.getId());
        seckillOrder.setOrderId(order.getId());

        redis.set(SeckillOrderPrefix.seckillOrderInfo, user.getId() + ":" + goodsVO.getId(), seckillOrder);
        seckillOrderDAO.save(seckillOrder);

        return order;
    }
}
