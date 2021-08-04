package com.shaw.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author shaw
 * @date 2021/6/16
 */
@Configuration
public class WebMvcSessionConfigure implements WebMvcConfigurer {

    @Autowired
    private SessionVerifyInterceptor sessionVerifyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionVerifyInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/registry");
    }
}
