package com.shaw.kratos.dao.mapper;

import com.shaw.kratos.dao.IBaseDAO;
import com.shaw.kratos.dto.user.UserDO;

public interface UserMapper extends IBaseDAO<UserDO> {

    UserDO getByUid(String uid);
}
