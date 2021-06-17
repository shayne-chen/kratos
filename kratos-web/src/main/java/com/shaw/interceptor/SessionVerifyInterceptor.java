package com.shaw.interceptor;

import com.shaw.kratos.common.enums.KratosExceptionEnum;
import com.shaw.kratos.common.exceptions.BusinessException;
import com.shaw.kratos.service.impl.UserCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenxiao
 * @date 2021/6/16 3:47 下午
 */
@Component
@Slf4j
public class SessionVerifyInterceptor implements HandlerInterceptor {

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            throw new BusinessException(KratosExceptionEnum.SESSION_MISSED);
        }
        Cookie sessionCookie = null;
        for (Cookie cookie: cookies) {
            if ("sid".equals(cookie.getName())) {
                sessionCookie = cookie;
                break;
            }
        }
        if (null == sessionCookie) {
            throw new BusinessException(KratosExceptionEnum.SESSION_MISSED);
        }
        String sid = sessionCookie.getValue();
        if (!userCacheService.verifySession(sid)) {
            throw new BusinessException(KratosExceptionEnum.SESSION_INVALID);
        }
        return true;
    }
}
