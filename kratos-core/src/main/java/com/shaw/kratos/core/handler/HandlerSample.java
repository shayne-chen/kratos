package com.shaw.kratos.core.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shaw
 * @date 2021/6/22
 */
@Component
public class HandlerSample implements Handler, InitializingBean {

    @Autowired
    private DefaultHandlerChain handlerChain;

    @Override
    public void doHandle(HttpServletRequest request, HttpServletResponse response, HandlerChain handlerChain) {
        // do something
        handlerChain.doHandle(request, response);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        handlerChain.registerHandler(this, 1);
    }
}