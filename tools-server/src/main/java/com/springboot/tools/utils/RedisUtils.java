package com.springboot.tools.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * @author shaowen
 */
@Component
public class RedisUtils {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 存放string类型
     * @param key
     * @param data
     * @param timeout
     */
    public void setString(String key, String data, int timeout) {
        try {
            stringRedisTemplate.opsForValue().set(key, data);
            if (timeout != -1) {
                stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 存放string类型
     * @param key
     * @param data
     */
    public void setString(String key, String data) {
        setString(key, data, -1);
    }

    /**
     * 根据key查询string类型
     * @param key
     * @return
     */
    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 根据对应的key删除key
     * @param key
     */
    public Boolean delKey(String key) {
        return stringRedisTemplate.delete(key);
    }

}
