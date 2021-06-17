package com.shaw.kratos.service.user;

import com.shaw.kratos.dto.user.UserSessionDO;

public interface IUserSessionService {

    void add(UserSessionDO userSessionDO);

    void expireAllSessions(String uid);

    UserSessionDO getSessions(String uid);

    UserSessionDO getBySid(String sid);

}
