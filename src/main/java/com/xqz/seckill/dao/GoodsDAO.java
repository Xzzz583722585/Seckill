package com.xqz.seckill.dao;

import com.xqz.seckill.domain.Goods;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface GoodsDAO extends JpaRepository<Goods, Integer>{
    @Query(value = "SELECT g.*, sg.price seckill_price, sg.stock seckill_stock, sg.start_date, sg.end_date\n" +
            "FROM goods g\n" +
            "JOIN seckill_goods sg\n" +
            "ON g.id = sg.goods_id", nativeQuery = true)
    Collection<GoodsVO> findAllSeckillGoods();

    @Query(value = "SELECT g.*, sg.price seckill_price, sg.stock seckill_stock, sg.start_date, sg.end_date\n" +
            "FROM goods g\n" +
            "JOIN seckill_goods sg\n" +
            "ON g.id = sg.goods_id\n" +
            "WHERE g.id = :goodsId", nativeQuery = true)
    GoodsVO findSeckillGoodsById(@Param("goodsId") Long goodsId);
}
