package com.shaw.kratos.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void set(String key, String value, long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
    }

    public Boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

    public void leftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public String rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    public void putHash(String redisKey, String key, String value) {
        redisTemplate.opsForHash().put(redisKey, key, value);
    }

    public String getHash(String redisKey, String key) {
        return String.valueOf(redisTemplate.opsForHash().get(redisKey, key));
    }

    public void lock(String key, String value, long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, timeUnit);
    }

    public void unLock(String key) {
        redisTemplate.delete(key);
    }
}
