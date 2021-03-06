package com.shaw.kratos.core.aop;

import com.shaw.kratos.core.source.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author shaw
 * @date 2021/6/17
 */
@Component
@Order(-1)
@Aspect
@Slf4j
public class DynamicDataSourceAspect {

    /** 切换数据源 */
    @Before("@annotation(dataSource))")
    public void switchDataSource(JoinPoint point, DataSource dataSource) {
        if (!DynamicDataSourceContextHolder.containsDataSourceKey(dataSource.value())) {
            log.error("dataSource value = {}, 不支持", dataSource.value());
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value());
            log.info("切换数据源 = " + DynamicDataSourceContextHolder.getDataSourceKey());
        }
    }

    /** 重置为默认数据源 */
    @After("@annotation(dataSource))")
    public void afterResetDataSource(JoinPoint point, DataSource dataSource) {
        DynamicDataSourceContextHolder.resetDataSourceKey();
        log.info("重置后的数据源 = " + DynamicDataSourceContextHolder.getDataSourceKey());
    }
}
