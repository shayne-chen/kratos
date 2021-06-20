package com.shaw.kratos.service.user;

import com.shaw.kratos.dto.user.UserDO;

/**
 * @author chenxiao
 * @date 2021/6/17 5:21 下午
 */
public interface IUserCacheService {

    UserDO getBySid(String sid);

    void put(String sid, UserDO userDO);

    void deleteSid(String sid);

    /** 校验sid是否有效 */
    boolean verifySession(String sid);

}
