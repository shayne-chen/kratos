package com.shaw.kratos.dao.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * @author chenxiao
 * @date 2021/6/18 11:24 上午
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class} )
})
public class MybatisGmtInterceptor implements Interceptor {

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if ((!(invocation.getTarget() instanceof Executor)) || (invocation.getArgs().length != 2)) {
            return invocation.proceed();
        }
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if (ms.getSqlCommandType().equals(SqlCommandType.INSERT) || ms.getSqlCommandType().equals(SqlCommandType.UPDATE)) {
            Object paramObj = args[1];
            Class<?> clazz = paramObj.getClass();
            // DO类可能还有父类，通用字段一般写在父类中
            Field[] fields = clazz.getSuperclass().isInstance(Object.class) ? clazz.getDeclaredFields() : clazz.getSuperclass().getDeclaredFields();
            if (ms.getSqlCommandType().equals(SqlCommandType.INSERT)) {
                for (Field field: fields) {
                    switch (field.getName()) {
                        case "gmtCreate":
                            field.setAccessible(true);
                            field.set(paramObj, System.currentTimeMillis());
                        case "gmtModified":
                            field.setAccessible(true);
                            field.set(paramObj, System.currentTimeMillis());
                        default:
                            break;
                    }
                }
            } else {
                for (Field field: fields) {
                    if ("gmtModified".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(paramObj, System.currentTimeMillis());
                    }
                }
            }
        }

        return invocation.proceed();
    }

}
