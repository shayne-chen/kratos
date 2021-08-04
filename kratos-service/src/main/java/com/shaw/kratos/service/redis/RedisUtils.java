package com.shaw.kratos.service.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final long CACHE_TIMEOUT_MS = 100;

    public String get(String key) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String value = redisTemplate.opsForValue().get(key);
        stopWatch.stop();
        if (stopWatch.getTotalTimeMillis() > CACHE_TIMEOUT_MS) {
            log.warn("get from cache cost {} ms, cache key = {}", stopWatch.getTotalTimeMillis(), key);
        }
        return value;
    }

    public void put(String key, String value, long expireTime, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, expireTime, timeUnit);
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

    public Boolean lock(String key, String value, long expireTime, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, timeUnit);
    }

    public Boolean unLock(String key, String value) {
        if (!this.get(key).equals(value)) {
            return false;
        }
        return redisTemplate.delete(key);
    }
}
