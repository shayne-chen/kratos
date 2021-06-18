package com.shaw.kratos.service.impl;

import com.shaw.kratos.dao.mapper.user.UserMapper;
import com.shaw.kratos.dao.mapper.user.UserSessionMapper;
import com.shaw.kratos.dto.user.UserSessionDO;
import com.shaw.kratos.service.user.IUserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenxiao
 * @date 2021/6/16 4:15 下午
 */
@Service
public class UserSessionService implements IUserSessionService {

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private UserSessionMapper userSessionMapper;

    @Override
    public void add(UserSessionDO userSessionDO) {
        userSessionMapper.add(userSessionDO);
    }

    @Override
    public void expireAllSessions(String uid) {
        UserSessionDO userSessionDO = userSessionMapper.getByUid(uid);
        userSessionMapper.expireAllSid(uid);
        userCacheService.deleteSid(userSessionDO.getSid());
    }

    @Override
    public UserSessionDO getSessions(String uid) {
        return userSessionMapper.getByUid(uid);
    }

    @Override
    public UserSessionDO getBySid(String sid) {
        return userSessionMapper.getBySid(sid);
    }

    @Override
    public void addSession(String sid, String uid) {
        UserSessionDO userSessionDO = new UserSessionDO();
        userSessionDO.setSid(sid);
        userSessionDO.setUid(uid);
        userSessionDO.setStatus(true);
        this.add(userSessionDO);
    }
}
