package com.shaw.kratos.service.user;

import com.shaw.kratos.dto.user.UserDO;

public interface IUserService {

    UserDO getUserBySid(String sid);

    void userRegistry(UserDO userDO);

    UserDO getUserByUid(String uid);

}
