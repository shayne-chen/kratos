package com.shaw.kratos.core.source;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author chenxiao
 * @date 2021/6/17 10:22 上午
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected DataSource determineTargetDataSource() {
        return super.determineTargetDataSource();
    }

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
