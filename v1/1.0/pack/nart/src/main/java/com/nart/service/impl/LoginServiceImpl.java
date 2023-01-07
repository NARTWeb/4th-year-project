package com.nart.service.impl;

import com.nart.pojo.User;
import com.nart.service.DataCounterService;
import com.nart.service.LoginService;
import com.nart.service.UserService;
import com.nart.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: LoginServiceImpl
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/9/1 12:19
 */
@Transactional
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private DataCounterService dataCounterService;

    @Override
    public Result login(String uname, String pwd, HttpSession session) {
        if (StringUtils.isBlank(uname) || StringUtils.isBlank(pwd)) {
            return Result.fail(ErrorCode.PARAMS_ERROR);
        }
        System.out.println("未加密" + pwd);
        String password = EncryptUtil.encryptPwd(pwd);
        System.out.println("密码"+password);
        User user = userService.findUser(uname, password);
//        System.out.println(user);

        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_MATCH);
        }
        String token = EncryptUtil.createToken(Long.parseLong(user.getId()));
        redisUtil.set("TOKEN_" + token, user, RedisUtil.DEFAULT_EXPIRE);
        session.setAttribute("uid", "uid: " + user.getId());

        dataCounterService.updateOnlineUserAmount(true);
        return Result.success(token);
    }

    @Override
    public User checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> stringObjectMap = EncryptUtil.checkToken(token);
        if (stringObjectMap == null) {
            return null;
        }
        User user = redisUtil.get("TOKEN_" + token, User.class);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public Result logout(String token) {
        redisUtil.delete("TOKEN_" + token);
        userService.logout(UserThreadLocal.get().getId());
        dataCounterService.updateOnlineUserAmount(false);
        return Result.success(null);
    }

    @Override
    public Result register(String email, String uname, String pwd, HttpSession session) {
        if (email.length() == 0 || uname.length() == 0 || pwd.length() == 0) {
            return Result.fail(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.findUserByName(uname);
        if (user != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST);
        }
        String password = EncryptUtil.encryptPwd(pwd);
        User user1 = userService.register(email, uname, password);

        if(user1 == null) {
            return Result.fail(ErrorCode.REGISTER_ERROR);
        }
        String token = EncryptUtil.createToken(Long.parseLong(user1.getId()));

        redisUtil.set("TOKEN_" + token, user1, RedisUtil.DEFAULT_EXPIRE);
        session.setAttribute("uid", "uid: " + user1.getId());

        dataCounterService.updateUserAmount(true);
        dataCounterService.updateOnlineUserAmount(true);
        return Result.success(token);
    }
}
