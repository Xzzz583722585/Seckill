package com.xqz.seckill.service;

import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.vo.GoodsVO;

public interface OrderService {
    OrderInfo createOrder(User user, GoodsVO goodsVO);
}
