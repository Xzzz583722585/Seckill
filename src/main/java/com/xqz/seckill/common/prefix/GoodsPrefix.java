package com.xqz.seckill.common.prefix;

public class GoodsPrefix extends BasePrefix {

    private static final int SEC = 60;

    public static GoodsPrefix goodsList = new GoodsPrefix(SEC, "goods_list");
    public static GoodsPrefix goodsDetails = new GoodsPrefix(SEC, "goods_details");

    private GoodsPrefix(int expireSec, String prefix) {
        super(expireSec, prefix);
    }
}
