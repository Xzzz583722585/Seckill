package com.xqz.seckill.common.enums;

public enum OrderStatus {
    INITED(0, "新建未支付"),
    PAYED(1, "已支付"),
    DELIVERED(2, "已发货"),
    RECEIVED(3, "已收货"),
    REFUNDED(4, "已退款"),
    COMPLETED(5, "已完成");

    private int code;
    private String message;

    private OrderStatus(Object... args) {
        this.message = String.format(this.message, args);
    }

    private OrderStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getName() {
        return this.name();
    }

    public String toString() {
        return this.getName() + " : " +this.message;
    }
}
