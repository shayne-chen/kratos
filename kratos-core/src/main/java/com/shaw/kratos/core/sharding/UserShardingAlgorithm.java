package com.shaw.kratos.core.sharding;

import com.shaw.kratos.common.constants.UserSessionConstants;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
//import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

//public class UserShardingAlgorithm implements PreciseShardingAlgorithm<String> {
//
//    @Override
//    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
//        for (String table: collection) {
//            String target = UserSessionConstants.user_sharding_prefix + (preciseShardingValue.getValue().hashCode() % UserSessionConstants.user_sharding_num);
//            if (table.equals(target)) {
//                return table;
//            }
//        }
//        throw new UnsupportedOperationException();
//    }
//}
