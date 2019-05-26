package com.xqz.seckill.utils.token;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
