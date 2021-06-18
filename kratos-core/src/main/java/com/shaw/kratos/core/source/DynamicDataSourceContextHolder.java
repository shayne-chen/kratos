package com.shaw.kratos.core.source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chenxiao
 * @date 2021/6/17 10:38 上午
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "sharding";
        }
    };

    private static List<Object> dataSourceKeys = new ArrayList<>(3);

    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
    }

    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    public static void resetDataSourceKey() {
        contextHolder.remove();
    }

    public static boolean containsDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

    public static void addDataSourceKey(Collection<?> keys) {
        dataSourceKeys.addAll(keys);
    }
}
