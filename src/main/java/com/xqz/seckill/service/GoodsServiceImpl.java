package com.xqz.seckill.service;

import com.xqz.seckill.common.prefix.GoodsPrefix;
import com.xqz.seckill.common.prefix.SeckillGoodsPrefix;
import com.xqz.seckill.dao.GoodsDAO;
import com.xqz.seckill.utils.redis.RedisService;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    RedisService redis;
    @Autowired
    GoodsDAO goodsDAO;

    public Collection<GoodsVO> findAllSeckillGoods(){
        return goodsDAO.findAllSeckillGoods();
    }

    public GoodsVO findSeckillGoodsById(Long goodsId){
        GoodsVO goods = redis.get(GoodsPrefix.goods, goodsId.toString(), GoodsVO.class);
        if(goods == null){
            goods = goodsDAO.findSeckillGoodsById(goodsId);
            redis.set(GoodsPrefix.goods, goodsId.toString(), goods, GoodsPrefix.goods.getExpireSec());
        }

        return goods;
    }
}
