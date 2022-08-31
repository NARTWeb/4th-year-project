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
    public Result login(@RequestBody UserVo user) {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PutMapping("logout")
    public Result logout() {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @PostMapping("register")
    public Result register(@RequestBody UserVo user){
        return Result.fail(ErrorCode.UNDEFINED);
    }

    @GetMapping
    public Result showUserInfo() {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    public Result showUnameAvatar() {
        return Result.fail(ErrorCode.UNDEFINED);
    }

    public Result changeUserInfo(){ //
        return Result.fail(ErrorCode.UNDEFINED);
    }
}
