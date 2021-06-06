package com.shaw.controller;

import com.shaw.kratos.common.entity.Response;
import com.shaw.kratos.common.utils.ResponseUtils;
import com.shaw.kratos.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/get")
    public Response getUser(@RequestParam Long id) {
        return ResponseUtils.buildSuccessResponse(userService.getUser(id));
    }

}
