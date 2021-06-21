package com.shaw.kratos.service.user;

import com.shaw.kratos.dto.user.UserSessionDO;

public interface IUserSessionService {

    void add(UserSessionDO userSessionDO);

    void addSession(String sid, String uid);

    void expireAllSessions(String uid);

    void expireSessionAsync(String uid);

    UserSessionDO getSessions(String uid);

    UserSessionDO getBySid(String sid);

}
