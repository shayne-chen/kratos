package com.shaw.kratos.service.impl;

import com.shaw.kratos.common.constants.DataSourceConstants;
import com.shaw.kratos.core.aop.DataSource;
import com.shaw.kratos.dao.mapper.user.UserSessionMapper;
import com.shaw.kratos.dto.user.UserSessionDO;
import com.shaw.kratos.service.user.IUserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author chenxiao
 * @date 2021/6/16 4:15 下午
 */
@Service
@Slf4j
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
        handleExpiredSid(uid);
    }

    @Override
    @Async("kratosTask")
    @DataSource(value = DataSourceConstants.SHARDING_SOURCE)
    public void expireSessionAsync(String uid) {
        log.info("异步处理过期的sid, uid=" + uid);
        handleExpiredSid(uid);
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
        this.add(userSessionDO);
    }

    private void handleExpiredSid(String uid) {
        UserSessionDO userSessionDO = userSessionMapper.getByUid(uid);
        if (null == userSessionDO) {
            return;
        }
        UserSessionDO userSessionDO1 = new UserSessionDO();
        userSessionDO1.setStatus(0);
        userSessionDO1.setUid(uid);
        userSessionDO1.setSid(userSessionDO.getSid());
        userSessionMapper.expireAllSid(userSessionDO1);
        userCacheService.deleteSid(userSessionDO.getSid());
    }
}
