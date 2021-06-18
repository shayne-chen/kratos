package com.shaw.kratos.dao.mapper.user;

import com.shaw.kratos.dao.IBaseDAO;
import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.dto.user.UserSessionDO;

public interface UserMapper extends IBaseDAO<UserDO> {

    UserDO getByUid(String uid);

    UserDO getByUserAndPassword(String username, String password);

    UserDO getByUsername(String username);
}
