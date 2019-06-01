package com.xqz.seckill.controller;

import com.xqz.seckill.service.GoodsService;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/to_seckill_list")
    public String getAllSeckillGoods(Model model){
        model.addAttribute("goodsList", goodsService.findAllSeckillGoods());
        return "goods_list";
    }

    @GetMapping("/to_goods_detail")
    public String getSeckillGoodsById(Integer goodsId, Model model){
        GoodsVO seckillGoods = goodsService.findSeckillGoodsById(goodsId);
        Long now = System.currentTimeMillis();
        Integer seckillStatus;
        Integer remainSec;

        if(now < seckillGoods.getStartDate().getTime()){    // 秒杀还未开始，准备倒计时
            seckillStatus = 0;
            remainSec = (int)((seckillGoods.getStartDate().getTime() - now)/1000);
        } else if(now > seckillGoods.getEndDate().getTime()){   // 秒杀已经结束
            seckillStatus = 2;
            remainSec = -1;
        } else{   // 秒杀开始
            seckillStatus = 1;
            remainSec = 0;
        }

        model.addAttribute("goods", goodsService.findSeckillGoodsById(goodsId));
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("remainSec", remainSec);
        return "goods_detail";
    }
}
