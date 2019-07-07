package com.xqz.seckill.utils.coder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONHelper {
    public static <O> String toJSON(O o){
        return JSONObject.toJSONString(o, SerializerFeature.WriteMapNullValue);
    }

    public static <O> O fromJSON(String json, Class<O> clazz){
        return JSONObject.parseObject(json, clazz);
    }
}
