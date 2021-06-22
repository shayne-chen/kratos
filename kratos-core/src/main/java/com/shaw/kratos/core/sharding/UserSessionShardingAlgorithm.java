package com.shaw.kratos.core.sharding;

import com.shaw.kratos.common.constants.UserSessionConstants;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author chenxiao
 * @date 2021/6/17 2:56 下午
 */
public class UserSessionShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        int suffix = Math.abs(preciseShardingValue.getValue().hashCode() % UserSessionConstants.USER_SESSION_SHARDING_NUM);
        String target = UserSessionConstants.USER_SESSION_SHARDING_PREFIX + suffix;
        for (String each: collection) {
            if (each.equals(target)) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
