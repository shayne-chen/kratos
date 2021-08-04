package com.shaw.kratos.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shaw
 * @date 2021/6/22
 */
public interface Handler {

    void doHandle(HttpServletRequest request, HttpServletResponse response, HandlerChain handlerChain);
}
