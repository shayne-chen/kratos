package com.shaw.kratos.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shaw
 * @date 2021/6/22
 */
public interface HandlerChain {

    /** 请求处理器注册到责任链上 */
    void registerHandler(Handler handler, int position);

    /** 处理请求 */
    void doHandle(HttpServletRequest request, HttpServletResponse response);
}
