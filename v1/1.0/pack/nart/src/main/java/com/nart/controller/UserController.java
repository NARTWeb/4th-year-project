package com.nart.controller;

import com.nart.common.LogA;
import com.nart.service.UserService;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.UserVo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: UserController
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 13:25
 */
@RestController
@RequestMapping("/user")
@LogA
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("login")
    public Result login(@RequestBody UserVo uInfo) {
        boolean login = userService.login(uInfo.getUname(), uInfo.getPwd());
        if(login) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_MATCH);
    }

    @PutMapping("logout")
    public Result logout() {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PostMapping("register")
    public Result register(@RequestBody UserVo uInfo){
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping("info/{id}")
    public Result showUserInfo(@PathVariable("id") String id) {
        // if "" means show current user info
        // else means show other users' info
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping
    public Result showUnameAvatar() {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PutMapping("changeInfo")
    public Result changeUserInfo(@RequestBody UserVo uInfo){
        return Result.fail(ErrorCode.UNDEFINED);
    }
}
