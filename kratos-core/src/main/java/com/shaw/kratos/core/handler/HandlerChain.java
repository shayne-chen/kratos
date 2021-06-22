package com.shaw.kratos.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenxiao
 * @date 2021/6/22 10:52 上午
 */
public interface HandlerChain {

    void registryHandler(Handler handler, int position);

    void doHandle(HttpServletRequest request, HttpServletResponse response);
}
