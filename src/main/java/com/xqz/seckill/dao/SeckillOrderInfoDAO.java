package com.xqz.seckill.dao;

import com.xqz.seckill.domain.SeckillOrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeckillOrderInfoDAO extends JpaRepository<SeckillOrderInfo, Integer>{
    SeckillOrderInfo findByUserIdAndGoodsId(Long userId, Long goodsId);
}
