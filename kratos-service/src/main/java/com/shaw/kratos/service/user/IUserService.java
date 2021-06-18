package com.shaw.kratos.service.user;

import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.dto.user.UserSessionDO;

public interface IUserService {

    UserDO getUserBySid(String sid);

    UserSessionDO userRegistry(UserDO userDO);

    UserDO getUserByUid(String uid);

    UserSessionDO userLogin(UserDO userDO);

}
