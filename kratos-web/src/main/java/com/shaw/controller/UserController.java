package com.shaw.controller;

import com.shaw.kratos.common.entity.Response;
import com.shaw.kratos.common.utils.ResponseUtils;
import com.shaw.kratos.core.aop.DataSource;
import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    @DataSource(value = "dataSource")
    public Response userLogin(@RequestBody UserDO userDO) {
        return ResponseUtils.buildSuccessResponse(userService.userLogin(userDO));
    }

    @PostMapping(value = "/registry")
    @DataSource(value = "dataSource")
    public Response userRegistry(@RequestBody UserDO userDO) {
        return ResponseUtils.buildSuccessResponse(userService.userRegistry(userDO));
    }

    @GetMapping(value = "/get")
    @DataSource(value = "dataSource")
    public Response getUserInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String sid = null;
        for (Cookie cookie: cookies) {
            if ("sid".equals(cookie.getName())) {
                sid = cookie.getValue();
            }
        }
        return ResponseUtils.buildSuccessResponse(userService.getUserBySid(sid));

    }

}
