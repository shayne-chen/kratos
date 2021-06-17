package com.shaw.kratos.dto.user;

import com.shaw.kratos.dto.BaseDO;
import lombok.Data;

@Data
public class UserDO extends BaseDO<Long> {
    private String username;
    private String password;
    private String uid;

}
