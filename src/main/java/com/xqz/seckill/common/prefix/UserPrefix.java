package com.xqz.seckill.common.prefix;

public class UserPrefix extends BasePrefix {

    private static final int TOKEN_SEC = 3600*24;
    private static final int SESSION_SEC = 60*10;

    public static UserPrefix token = new UserPrefix(TOKEN_SEC, "token");
    public static UserPrefix user = new UserPrefix(SESSION_SEC, "user");

    private UserPrefix(int expireSec, String prefix) {
        super(expireSec, prefix);
    }
}