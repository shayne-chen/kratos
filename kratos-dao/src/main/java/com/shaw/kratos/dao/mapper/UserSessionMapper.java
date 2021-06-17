package com.shaw.kratos.dao.mapper;

import com.shaw.kratos.dao.IBaseDAO;
import com.shaw.kratos.dto.user.UserSessionDO;

/**
 * @author chenxiao
 * @date 2021/6/17 5:32 下午
 */
public interface UserSessionMapper extends IBaseDAO<UserSessionDO> {

    UserSessionDO getByUid(String uid);

    UserSessionDO getBySid(String sid);

    void expireAllSid(String uid);

}
