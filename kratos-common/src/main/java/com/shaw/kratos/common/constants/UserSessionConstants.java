package com.shaw.kratos.common.constants;

public class UserSessionConstants {

    /** 每个用户sid数量上限 */
    public static final int SESSION_LIMIT = 3;

    /** 缓存中的sid前缀，完整示例 kratos_user_session_{sid} */
    public static final String USER_SESSION_REDIS_PREFIX = "kratos_user_session_";

    /** sid过期时间 3600 * 30 s */
    public static final long USER_SESSION_EXPIRE_TIME = 3600 * 30 ;

    /** 用户表分表数量 */
    public static final int USER_SHARDING_NUM = 2;

    /** 用户表分表前缀 */
    public static final String USER_SHARDING_PREFIX = "user_";

    /** session表分表数量 */
    public static final int USER_SESSION_SHARDING_NUM = 4;

    /** session表分表前缀 */
    public static final String USER_SESSION_SHARDING_PREFIX = "user_session_";
}
