package com.shaw.kratos.core.source;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @author shaw
 * @date 2021/6/17
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    public void setDefaultDataSource(Object defaultDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
    }

    public void setDataSources(Map<Object, Object> dataSources) {
        super.setTargetDataSources(dataSources);
        DynamicDataSourceContextHolder.addDataSourceKey(dataSources.keySet());
    }
}
