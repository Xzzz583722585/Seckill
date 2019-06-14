package com.xqz.seckill.vo;

public class GoodsDetailsVO {

    private Integer seckillStatus;
    private Integer remainSeconds;
    private GoodsVO goods;

    public Integer getSeckillStatus() {
        return seckillStatus;
    }

    public void setSeckillStatus(Integer seckillStatus) {
        this.seckillStatus = seckillStatus;
    }

    public Integer getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(Integer remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GoodsDetailsVO{" +
                "seckillStatus=" + seckillStatus +
                ", remainSeconds=" + remainSeconds +
                ", goods=" + goods +
                '}';
    }
}
