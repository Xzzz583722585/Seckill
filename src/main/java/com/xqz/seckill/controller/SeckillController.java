package com.xqz.seckill.controller;

import com.xqz.seckill.common.enums.ResultStatus;
import com.xqz.seckill.common.prefix.GoodsPrefix;
import com.xqz.seckill.common.prefix.SeckillGoodsPrefix;
import com.xqz.seckill.common.result.ResultMsg;
import com.xqz.seckill.domain.OrderInfo;
import com.xqz.seckill.domain.SeckillOrderInfo;
import com.xqz.seckill.domain.User;
import com.xqz.seckill.security.SecurityContextHelper;
import com.xqz.seckill.service.GoodsService;
import com.xqz.seckill.service.SeckillService;
import com.xqz.seckill.utils.redis.RedisService;
import com.xqz.seckill.vo.GoodsVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {

    @Autowired
    SecurityContextHelper securityContextHelper;

    @Autowired
    RedisService redis;
    @Autowired
    GoodsService goodsService;
    @Autowired
    SeckillService seckillService;

    @PostMapping("/do_seckill")
    @ResponseBody
    public ResultMsg<OrderInfo> doSeckill(Long goodsId){
        // 判断库存
        Long seckillStock = redis.decr(SeckillGoodsPrefix.seckillGoodsStock, goodsId.toString(), Long.class);
        if(seckillStock < 0){
            return ResultMsg.build(ResultStatus.SECKILL_OVER);
        }

        // 判断是否已经秒杀过该商品
        User user = securityContextHelper.getUser();
        SeckillOrderInfo seckillOrder = seckillService
                .findSeckillOrderByUserIdAndGoodsId(user.getId(), goodsId);
        if(seckillOrder != null){
            return ResultMsg.build(ResultStatus.REPEATE_SECKILL);
        }

        // 秒杀
        OrderInfo order = seckillService.createSeckillOrder(user, goodsId);
        ResultMsg<OrderInfo> resultMsg = ResultMsg.build(ResultStatus.SUCCESS);
        resultMsg.setData(order);

        return resultMsg;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<GoodsVO> goodsList = goodsService.findAllSeckillGoods();
        for(GoodsVO goods: goodsList){
            redis.set(GoodsPrefix.goods, goods.getId().toString(), goods, GoodsPrefix.goods.getExpireSec());
            redis.set(SeckillGoodsPrefix.seckillGoodsStock, goods.getId().toString(), goods.getSeckillStock());
        }
    }
}
