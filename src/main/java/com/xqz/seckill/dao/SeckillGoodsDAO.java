package com.xqz.seckill.dao;

import com.xqz.seckill.domain.SeckillGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SeckillGoodsDAO extends JpaRepository<SeckillGoods, Integer>{
    @Modifying
    @Query(value = "UPDATE seckill_goods SET stock = stock - :num WHERE goods_id = :goodsId and stock > 0", nativeQuery = true)
    int reduceStock(@Param("num") Integer num, @Param("goodsId") Long goodsId);
}
