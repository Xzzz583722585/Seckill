package com.xqz.seckill.common.enums;

public enum OrderChannel {
    WEB(1, "网页端"),
    ANDROID(2, "安卓端"),
    IOS(3, "苹果端");

    private int code;
    private String message;

    private OrderChannel(Object... args) {
        this.message = String.format(this.message, args);
    }

    private OrderChannel(int code, String message) {
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
