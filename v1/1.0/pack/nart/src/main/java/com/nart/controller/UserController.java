package com.nart.controller;

import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.vo.UserVo;
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
public class UserController {

    @PutMapping("login")
    public Result login(@RequestBody UserVo uInfo) {
        return Result.fail(ErrorCode.UNDEFINED);
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
