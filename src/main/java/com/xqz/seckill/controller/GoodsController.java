package com.xqz.seckill.controller;

import com.xqz.seckill.common.prefix.GoodsPrefix;
import com.xqz.seckill.common.prefix.SeckillGoodsPrefix;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.security.SecurityContextHelper;
import com.xqz.seckill.service.GoodsService;
import com.xqz.seckill.utils.redis.RedisService;
import com.xqz.seckill.utils.string.StringUtil;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
@ResponseBody
public class GoodsController {

    @Autowired
    RedisService redis;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    SecurityContextHelper securityContextHelper;

    @Autowired
    GoodsService goodsService;

    @GetMapping(value = "/to_seckill_list", produces = "text/html")
    public String getAllSeckillGoods(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = securityContextHelper.getUser();
        String html = redis.get(SeckillGoodsPrefix.seckillGoodsList, "", String.class);
        if(!StringUtil.isEmpty(html)){
            return html;
        }

        model.addAttribute("goodsVOList", goodsService.findAllSeckillGoods());
        html = thymeleafViewResolver.getTemplateEngine().process("goods/goods_list",
                new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap()));
        if(!StringUtil.isEmpty(html)){
            redis.set(GoodsPrefix.goodsList, "", html, GoodsPrefix.goodsList.getExpireSec());
        }

        return html;
    }

    @GetMapping(value = "/to_goods_detail", produces = "text/html")
    public String getSeckillGoodsById(HttpServletRequest request, HttpServletResponse response, Long goodsId, Model model){
        String html = redis.get(GoodsPrefix.goodsDetails, goodsId.toString(), String.class);
        if(!StringUtil.isEmpty(html)){
            return html;
        }

        GoodsVO goodsVO = goodsService.findSeckillGoodsById(goodsId);
        Long now = System.currentTimeMillis();
        Integer seckillStatus;
        Integer remainSec;

        if(now < goodsVO.getStartDate().getTime()){    // 秒杀还未开始，准备倒计时
            seckillStatus = 0;
            remainSec = (int)((goodsVO.getStartDate().getTime() - now)/1000);
        } else if(now > goodsVO.getEndDate().getTime()){   // 秒杀已经结束
            seckillStatus = 2;
            remainSec = -1;
        } else{   // 秒杀开始
            seckillStatus = 1;
            remainSec = 0;
        }

        model.addAttribute("goodsVO", goodsVO);
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("remainSec", remainSec);
        html = thymeleafViewResolver.getTemplateEngine().process("goods/goods_detail",
                new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap()));
        if(!StringUtil.isEmpty(html)){
            redis.set(GoodsPrefix.goodsDetails, goodsId.toString(), html, GoodsPrefix.goodsDetails.getExpireSec());
        }

        return html;
    }
}
