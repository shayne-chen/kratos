package com.shaw.kratos.service.impl;

import com.shaw.kratos.common.enums.KratosExceptionEnum;
import com.shaw.kratos.common.exceptions.BusinessException;
import com.shaw.kratos.common.utils.UidUtils;
import com.shaw.kratos.dao.mapper.user.UserMapper;
import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.dto.user.UserSessionDO;
import com.shaw.kratos.service.user.IUserService;
import com.shaw.kratos.service.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserSessionService userSessionService;

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public UserDO getUserBySid(String sid) {
        UserDO userDO = userCacheService.getBySid(sid);
        if (null != userDO) {
            return userDO;
        }
        UserSessionDO userSessionDO = userSessionService.getBySid(sid);
        UserDO userDO1 = userMapper.getByUid(userSessionDO.getUid());
        userCacheService.put(sid, userDO1);
        return userDO1;
    }

    @Override
    @Transactional
    public UserSessionDO userRegistry(UserDO userDO) {
        if (StringUtils.isEmpty(userDO.getUid())) {
            userDO.setUid(UidUtils.generateUid());
        }
        userMapper.add(userDO);

        String newSid = UidUtils.generateSid(userDO.getUid());

        UserSessionDO userSessionDO = new UserSessionDO();
        userSessionDO.setSid(newSid);
        userSessionDO.setUid(userDO.getUid());
        userSessionService.add(userSessionDO);

        userCacheService.put(newSid, userDO);
        return userSessionDO;
    }

    @Override
    public UserDO getUserByUid(String uid) {
        return userMapper.getByUid(uid);
    }

    @Override
    public UserSessionDO userLogin(UserDO userDO) {
        UserDO userDO1 = userMapper.getByUsername(userDO.getUsername());
        if (null == userDO1) {
            throw new BusinessException(KratosExceptionEnum.USER_NOT_EXIST);
        }
        if (!userDO1.getPassword().equals(userDO.getPassword())) {
            throw new BusinessException(KratosExceptionEnum.PASSWORD_ERROR);
        }
        userSessionService.expireAllSessions(userDO1.getUid());

        String newSid = UidUtils.generateSid(userDO1.getUid());

        UserSessionDO userSessionDO = new UserSessionDO();
        userSessionDO.setSid(newSid);
        userSessionDO.setUid(userDO1.getUid());
        userSessionDO.setStatus(true);
        userSessionService.add(userSessionDO);
        userCacheService.put(newSid, userDO1);

        return userSessionDO;
    }
}
