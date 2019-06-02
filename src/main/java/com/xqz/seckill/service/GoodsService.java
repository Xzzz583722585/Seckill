package com.xqz.seckill.service;

import com.xqz.seckill.vo.GoodsVO;

import java.util.Collection;

public interface GoodsService {
    Collection<GoodsVO> findAllSeckillGoods();
    GoodsVO findSeckillGoodsById(Long goodsId);
}
