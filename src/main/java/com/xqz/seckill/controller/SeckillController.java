package com.xqz.seckill.controller;

import com.xqz.seckill.common.enums.ResultStatus;
import com.xqz.seckill.common.result.ResultMsg;
import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.SeckillOrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.security.LoginUserDetails;
import com.xqz.seckill.security.SecurityContextHelper;
import com.xqz.seckill.service.GoodsService;
import com.xqz.seckill.service.SeckillService;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    SecurityContextHelper securityContextHelper;

    @Autowired
    GoodsService goodsService;
    @Autowired
    SeckillService seckillService;

    @GetMapping("/do_seckill")
    public String doSeckill(Long goodsId, Model model){
        // 判断库存
        GoodsVO goodsVO = goodsService.findSeckillGoodsById(goodsId);
        if(goodsVO.getSeckillStock() <= 0){
            model.addAttribute("errmsg", ResultMsg.build(ResultStatus.SECKILL_OVER));
            return "seckill/seckill_fail";
        }

        // 判断是否已经秒杀过该商品
        User user = securityContextHelper.getUser();
        SeckillOrderInfo seckillOrder = seckillService
                .findSeckillOrderByUserIdAndGoodsId(user.getId(), goodsId);
        if(seckillOrder != null){
            model.addAttribute("errmsg", ResultMsg.build(ResultStatus.REPEATE_SECKILL));
            return "seckill/seckill_fail";
        }

        // 秒杀
        OrderInfo order = seckillService.createSeckillOrder(user, goodsVO);
        model.addAttribute("goodsVO", goodsVO);
        model.addAttribute("orderInfo", order);

        return "order/order_detail";
    }
}
