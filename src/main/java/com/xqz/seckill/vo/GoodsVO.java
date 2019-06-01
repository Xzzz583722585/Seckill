package com.xqz.seckill.vo;

import com.xqz.seckill.domain.Goods;

import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class GoodsVO extends Goods {

    private Double seckillPrice;
    private Integer seckillStock;
    private Timestamp startDate;
    private Timestamp endDate;

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public Integer getSeckillStock() {
        return seckillStock;
    }

    public void setSeckillStock(Integer seckillStock) {
        this.seckillStock = seckillStock;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString() + " GoodsVO{" +
                "seckillPrice=" + seckillPrice +
                ", seckillStock=" + seckillStock +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
