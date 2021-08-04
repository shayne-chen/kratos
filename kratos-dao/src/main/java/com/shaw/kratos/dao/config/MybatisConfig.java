package com.shaw.kratos.dao.config;

import com.shaw.kratos.common.constants.DataSourceConstants;
import com.shaw.kratos.core.source.DynamicDataSource;
import com.shaw.kratos.dao.interceptor.MybatisGmtInterceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shaw
 * @date 2021/6/17
 */
@Configuration
public class MybatisConfig {

    @Resource(name = "shardingDataSource")
    private DataSource shardingDataSource;

    @Resource(name = "defaultDataSource")
    private DataSource defaultDataSource;

    @Resource
    private MybatisGmtInterceptor mybatisGmtInterceptor;

    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceConstants.DEFAULT_SOURCE, defaultDataSource);
        dataSourceMap.put(DataSourceConstants.SHARDING_SOURCE, shardingDataSource);

        dynamicDataSource.setDefaultDataSource(defaultDataSource);
        dynamicDataSource.setDataSources(dataSourceMap);
        return dynamicDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDataSource());
        sqlSessionFactory.setTypeAliasesPackage("com.shaw.kratos.dto");
        sqlSessionFactory.setPlugins(mybatisGmtInterceptor);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:mybatis/mapper/*.xml"));    // 扫描映射文件
        return sqlSessionFactory;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
