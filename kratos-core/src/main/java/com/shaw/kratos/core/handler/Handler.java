package com.shaw.kratos.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenxiao
 * @date 2021/6/22 9:55 上午
 */
public interface Handler {

    void doHandle(HttpServletRequest request, HttpServletResponse response, HandlerChain handlerChain);
}
