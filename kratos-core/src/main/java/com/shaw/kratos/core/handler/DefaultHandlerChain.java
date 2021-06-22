package com.shaw.kratos.core.handler;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenxiao
 * @date 2021/6/22 9:56 上午
 */
@Component
public class DefaultHandlerChain extends AbstractHandlerChain {

    @Override
    public void doHandle(HttpServletRequest request, HttpServletResponse response) {
        if (index > handlerMap.size()) {
            return;
        }
        Handler handler = handlerMap.get(index);
        index++;
        handler.doHandle(request, response, this);
    }
}
