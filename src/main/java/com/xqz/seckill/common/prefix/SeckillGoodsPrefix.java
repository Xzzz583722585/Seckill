package com.xqz.seckill.common.prefix;

public class SeckillGoodsPrefix extends BasePrefix {

    private static final int SEC = 10;

    public static SeckillGoodsPrefix seckillGoodsStock = new SeckillGoodsPrefix(SEC, "seckill_goods_stock");
    public static SeckillGoodsPrefix seckillGoodsList = new SeckillGoodsPrefix(SEC, "seckill_goods_list");

    private SeckillGoodsPrefix(int expireSec, String prefix) {
        super(expireSec, prefix);
    }
}
