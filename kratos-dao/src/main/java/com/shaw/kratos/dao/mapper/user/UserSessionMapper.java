package com.shaw.kratos.dao.mapper.user;

import com.shaw.kratos.dao.IBaseDAO;
import com.shaw.kratos.dto.user.UserSessionDO;

/**
 * @author chenxiao
 * @date 2021/6/18 3:21 下午
 */
public interface UserSessionMapper extends IBaseDAO<UserSessionDO> {

    UserSessionDO getByUid(String uid);

    UserSessionDO getBySid(String sid);

    void expireAllSid(UserSessionDO userSessionDO);

}
