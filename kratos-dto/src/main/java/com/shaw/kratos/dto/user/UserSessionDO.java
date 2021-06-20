package com.shaw.kratos.dto.user;

import com.shaw.kratos.dto.BaseDO;
import lombok.Builder;
import lombok.Data;

@Data
public class UserSessionDO extends BaseDO<Long> {

    private String uid;

    private String sid;

    /** sid是否有效，1-有效，0-无效 */
    private Integer status = 1;
}
