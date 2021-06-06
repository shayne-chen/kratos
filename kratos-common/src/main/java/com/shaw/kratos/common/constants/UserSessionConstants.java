package com.shaw.kratos.common.constants;

public class UserSessionConstants {

    /** 每个用户sid数量上限 */
    public static final int session_limit = 3;

    /** 缓存中的sid前缀，完整示例 user_session_{sid} */
    public static final String user_session_redis_prefix = "user_session_";

    /** sid过期时间 3600 * 30 s */
    public static final long user_session_expire_time = 3600 * 30 ;

    /** 用户表分表数量 */
    public static final int user_sharding_num = 2;

    /** 用户表分表前缀 */
    public static final String user_sharding_prefix = "user_";

    /** session表分表数量 */
    public static final int user_session_sharding_num = 4;

    /** session表分表前缀 */
    public static final String user_session_sharding_prefix = "user_session_";
}
