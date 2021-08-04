package com.shaw.kratos.core.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shaw
 * @date 2021/6/22
 */
public abstract class AbstractHandlerChain implements HandlerChain {

    Map<Integer, Handler> handlerMap = new HashMap<>();

    int index = 1;

    @Override
    public synchronized void registerHandler(Handler handler, int position) {
        position = handlerMap.get(position) == null ? position : position + 1;
        handlerMap.put(position, handler);
    }

    @Override
    public abstract void doHandle(HttpServletRequest request, HttpServletResponse response);
}
