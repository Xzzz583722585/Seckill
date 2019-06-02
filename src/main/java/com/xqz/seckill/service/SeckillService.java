package com.xqz.seckill.service;

import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.SeckillOrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.vo.GoodsVO;

public interface SeckillService {
    SeckillOrderInfo findSeckillOrderByUserIdAndGoodsId(Long userId, Long goodsId);
    OrderInfo createSeckillOrder(User user, GoodsVO goodsVO);
}
