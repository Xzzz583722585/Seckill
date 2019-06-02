package com.xqz.seckill.service;

import com.xqz.seckill.dao.GoodsDAO;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDAO goodsDAO;

    public Collection<GoodsVO> findAllSeckillGoods(){
        return goodsDAO.findAllSeckillGoods();
    }

    public GoodsVO findSeckillGoodsById(Long goodsId){
        return goodsDAO.findSeckillGoodsById(goodsId);
    }
}
