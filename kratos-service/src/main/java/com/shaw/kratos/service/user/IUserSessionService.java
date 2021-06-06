package com.shaw.kratos.service.user;

import com.shaw.kratos.dto.user.UserSessionDO;

import java.util.List;

public interface IUserSessionService {

    void expireAllSessions(String uid);

    List<UserSessionDO> getSessions(String uid);

    void expireSessions(List<Long> ids);
}
