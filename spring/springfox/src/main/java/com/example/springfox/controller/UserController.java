package com.example.springfox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springfox.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author wuxii@foxmail.com
 */
@Api("用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation("获取用户信息")
    @GetMapping("/u/{username}")
    public User find(@ApiParam(name = "username", value = "用户名", required = true) @PathVariable("username") String username) {
        return new User(username, "bar");
    }

}
