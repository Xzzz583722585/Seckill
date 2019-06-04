package com.xqz.seckill.common.prefix;

public class UserPrefix extends BasePrefix {

    private static final int SEC = 3600*24;

    public static UserPrefix token = new UserPrefix(SEC, "token");

    private UserPrefix(int expireSec, String prefix) {
        super(expireSec, prefix);
    }
}
