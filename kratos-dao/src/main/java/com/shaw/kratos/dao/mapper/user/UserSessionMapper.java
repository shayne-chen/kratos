package com.shaw.kratos.dao.mapper.user;

import com.shaw.kratos.dao.IBaseDAO;
import com.shaw.kratos.dto.user.UserSessionDO;

/**
 * @author shaw
 * @date 2021/6/18
 */
public interface UserSessionMapper extends IBaseDAO<UserSessionDO> {

    UserSessionDO getByUid(String uid);

    UserSessionDO getBySid(String sid);

    void expireAllSid(UserSessionDO userSessionDO);

}
