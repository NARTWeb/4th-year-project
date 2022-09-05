package com.nart.controller;

import com.nart.common.LogA;
import com.nart.pojo.User;
import com.nart.service.LoginService;
import com.nart.service.UserService;
import com.nart.util.ErrorCode;
import com.nart.util.Result;
import com.nart.util.UserThreadLocal;
import com.nart.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    @Autowired
    private LoginService loginService;

    @PutMapping("login")
    public Result login(@RequestBody UserVo uInfo, HttpSession session) {
        return loginService.login(uInfo.getUname(), uInfo.getPwd(), session);
    }

    @PutMapping("logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        return loginService.logout(token);
    }

    @PostMapping("register")
    public Result register(@RequestBody UserVo uInfo, HttpSession session){
        return loginService.register(uInfo.getEmail(), uInfo.getUname(), uInfo.getPwd(), session);
    }

    @GetMapping("info/{id}")
    public Result showUserInfo(@PathVariable("id") String id) {
        User user;
        if(id.isEmpty()) {
            user = userService.showUserInfo(UserThreadLocal.get().getId());
        } else {
            user = userService.showUserInfo(id);
        }
        if(user == null) {
            Result.fail(ErrorCode.USER_NOT_EXIST);
        }
        return Result.success(user);
    }

    @GetMapping
    public Result showUnameAvatar() {
        User user = userService.showUnameAvatar(UserThreadLocal.get().getId());
        return Result.success(user);
    }

    @PutMapping("changeInfo")
    public Result changeUserInfo(@RequestBody UserVo uInfo){
        boolean b = userService.changeUserInfo(uInfo, UserThreadLocal.get().getId());
        if(b) {
            return Result.success(null);
        }
        return Result.fail(ErrorCode.CHANGE_INFO_ERROR);
    }
}
