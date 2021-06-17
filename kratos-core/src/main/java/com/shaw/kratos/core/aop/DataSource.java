package com.shaw.kratos.core.aop;

import java.lang.annotation.*;

/**
 * @author chenxiao
 * @date 2021/6/17 10:50 上午
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    String value();
}
