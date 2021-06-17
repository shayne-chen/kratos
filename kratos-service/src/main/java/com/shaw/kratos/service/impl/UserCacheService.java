package com.shaw.kratos.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shaw.kratos.common.constants.UserSessionConstants;
import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.service.redis.RedisUtils;
import com.shaw.kratos.service.user.IUserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author chenxiao
 * @date 2021/6/17 5:22 下午
 */
@Service
public class UserCacheService implements IUserCacheService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public UserDO getBySid(String sid) {
        String key = UserSessionConstants.USER_SESSION_REDIS_PREFIX + sid;
        return JSONObject.parseObject(redisUtils.get(key), UserDO.class);
    }

    @Override
    public void put(String sid, UserDO userDO) {
        String key = UserSessionConstants.USER_SESSION_REDIS_PREFIX + sid;
        redisUtils.set(key, JSON.toJSONString(userDO), UserSessionConstants.USER_SESSION_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void deleteSid(String sid) {
        String key = UserSessionConstants.USER_SESSION_REDIS_PREFIX + sid;
        redisUtils.deleteKey(key);
    }

    @Override
    public boolean verifySession(String sid) {
        String key = UserSessionConstants.USER_SESSION_REDIS_PREFIX + sid;
        return redisUtils.hasKey(key);
    }

}
