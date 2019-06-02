package com.xqz.seckill.service;

import com.xqz.seckill.common.enums.OrderChannel;
import com.xqz.seckill.common.enums.OrderStatus;
import com.xqz.seckill.dao.OrderInfoDAO;
import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderInfoDAO orderDAO;

    @Override
    public OrderInfo createOrder(User user, GoodsVO goodsVO) {
        OrderInfo order = new OrderInfo();
        order.setCreateDate(new Timestamp(new Date().getTime()));
        order.setGoods(goodsVO);
        order.setUserId(user.getId());
        order.setGoodsCount(1);
        order.setGoodsPrice(goodsVO.getSeckillPrice());
        order.setOrderChannel(OrderChannel.WEB.getCode());
        order.setStatus(OrderStatus.INITED.getCode());

        return orderDAO.save(order);
    }
}
