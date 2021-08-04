package com.shaw.kratos.core.aop;

import java.lang.annotation.*;

/**
 * @author shaw
 * @date 2021/6/17
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    String value();
}
