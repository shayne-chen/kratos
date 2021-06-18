package com.shaw.controller;

import com.shaw.kratos.common.entity.Response;
import com.shaw.kratos.common.entity.ResponseStatus;
import com.shaw.kratos.common.utils.ResponseUtils;
import com.shaw.kratos.core.aop.DataSource;
import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    @DataSource(value = "sharding")
    public ResponseStatus userLogin(@RequestBody UserDO userDO) {
        userService.userLogin(userDO);
        return ResponseUtils.success();
    }

    @PostMapping(value = "/registry")
    @DataSource(value = "sharding")
    public ResponseStatus userRegistry(@RequestBody UserDO userDO) {
        userService.userRegistry(userDO);
        return ResponseUtils.success();
    }

}
