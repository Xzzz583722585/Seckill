package com.xqz.seckill.utils.redis;

import com.xqz.seckill.common.prefix.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate stringRedisTemplate;

    public void set(KeyPrefix prefix, String k, Object v, long exp){
        if(v instanceof String){
            stringRedisTemplate.opsForValue().set(prefix.getPrefix() + k, v, exp, TimeUnit.SECONDS);
        }else{
            redisTemplate.opsForValue().set(prefix.getPrefix() + k, v, exp, TimeUnit.SECONDS);
        }
    }

    public void set(KeyPrefix prefix, String k, Object v){
        if(v instanceof String){
            stringRedisTemplate.opsForValue().set(prefix.getPrefix() + k, v);
        }else{
            redisTemplate.opsForValue().set(prefix.getPrefix() + k, v);
        }
    }

    public <T> T get(KeyPrefix prefix, String k, Class<T> valueClazz){
        if(valueClazz == String.class){
            return valueClazz.cast(stringRedisTemplate.opsForValue().get(prefix.getPrefix() + k));
        }else{
            return valueClazz.cast(redisTemplate.opsForValue().get(prefix.getPrefix() + k));
        }
    }

    public <T> boolean exist(KeyPrefix prefix, String k, Class<T> valueClazz){
        Object v = null;
        if(valueClazz == String.class){
            v = stringRedisTemplate.opsForValue().get(prefix.getPrefix() + k);
        }else{
            v = redisTemplate.opsForValue().get(prefix.getPrefix() + k);
        }
        return v != null;
    }

    public <T> Long incr(KeyPrefix prefix, String k, Class<T> valueClazz){
        if(valueClazz == String.class){
            return stringRedisTemplate.opsForValue().increment(prefix.getPrefix() + k);
        }else{
            return redisTemplate.opsForValue().increment(prefix.getPrefix() + k);
        }
    }

    public <T> Long decr(KeyPrefix prefix, String k, Class<T> valueClazz){
        if(valueClazz == String.class){
            return stringRedisTemplate.opsForValue().decrement(prefix.getPrefix() + k);
        }else{
            return redisTemplate.opsForValue().decrement(prefix.getPrefix() + k);
        }
    }
}
