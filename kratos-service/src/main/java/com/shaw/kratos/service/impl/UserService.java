package com.shaw.kratos.service.impl;

import com.alibaba.fastjson.JSON;
import com.shaw.kratos.dao.mapper.UserMapper;
import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.service.user.IUserService;
import com.shaw.kratos.service.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    private final String user_key_prefix = "kratos_user_";

    @Override
    public UserDO getUser(Long id) {
        String key = user_key_prefix + id;
        if (redisUtils.hasKey(key)) {
            log.info("从缓存中获取用户信息成功");
            return JSON.parseObject(redisUtils.get(key), UserDO.class);
        }
        log.info("缓存数据不存在");
        UserDO userDO = userMapper.getById(id);
        redisUtils.set(key, JSON.toJSONString(userDO), 3600, TimeUnit.SECONDS);
        return userMapper.getById(id);
    }

    @Override
    public void userRegistry(UserDO userDO) {

    }

    @Override
    public UserDO getUserByUid(String uid) {
        return userMapper.getByUid(uid);
    }

    @Override
    public void addUser(UserDO userDO) {
        userMapper.add(userDO);
    }

    @Override
    public void updateUser(UserDO userDO) {
        userMapper.update(userDO);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }
}
