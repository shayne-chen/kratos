package com.shaw.kratos.service.user;

import com.shaw.kratos.dto.user.UserDO;

public interface IUserService {

    UserDO getUser(Long id);

    void userRegistry(UserDO userDO);

    UserDO getUserByUid(String uid);

    void addUser(UserDO userDO);

    void updateUser(UserDO userDO);

    void deleteById(Long id);
}
