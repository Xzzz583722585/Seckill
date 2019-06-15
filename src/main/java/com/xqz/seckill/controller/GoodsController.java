package com.xqz.seckill.controller;

import com.xqz.seckill.security.SecurityContextHelper;
import com.xqz.seckill.service.GoodsService;
import com.xqz.seckill.utils.redis.RedisService;
import com.xqz.seckill.vo.GoodsDetailsVO;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping("/goods")
@ResponseBody
public class GoodsController {

    @Autowired
    RedisService redis;
    @Autowired
    SecurityContextHelper securityContextHelper;

    @Autowired
    GoodsService goodsService;

    @GetMapping(value = "/seckill_list")
    public Collection<GoodsVO> getAllSeckillGoods(){
        return goodsService.findAllSeckillGoods();
    }

    @GetMapping(value = "/goods_detail")
    public GoodsDetailsVO getSeckillGoodsById(Long goodsId){
        GoodsVO goodsVO = goodsService.findSeckillGoodsById(goodsId);
        Long now = System.currentTimeMillis();
        Integer seckillStatus;
        Integer remainSeconds;

        if(now < goodsVO.getStartDate().getTime()){    // 秒杀还未开始，准备倒计时
            seckillStatus = 0;
            remainSeconds = (int)((goodsVO.getStartDate().getTime() - now)/1000);
        } else if(now > goodsVO.getEndDate().getTime()){   // 秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else{   // 秒杀开始
            seckillStatus = 1;
            remainSeconds = 0;
        }

        GoodsDetailsVO goodsDetails = new GoodsDetailsVO();
        goodsDetails.setSeckillStatus(seckillStatus);
        goodsDetails.setRemainSeconds(remainSeconds);
        goodsDetails.setGoods(goodsVO);

        return goodsDetails;
    }
}
