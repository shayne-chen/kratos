package com.shaw.controller;

import com.shaw.kratos.common.entity.Response;
import com.shaw.kratos.common.entity.ResponseStatus;
import com.shaw.kratos.common.utils.ResponseUtils;
import com.shaw.kratos.core.aop.DataSource;
import com.shaw.kratos.dto.user.UserDO;
import com.shaw.kratos.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    @DataSource(value = "sharding")
    public ResponseStatus userLogin(@RequestBody UserDO userDO) {
        userService.userRegistry(userDO);
        return ResponseUtils.success();
    }

}
