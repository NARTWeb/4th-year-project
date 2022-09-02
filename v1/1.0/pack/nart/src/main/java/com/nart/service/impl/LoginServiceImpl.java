package com.nart.service.impl;

import com.nart.pojo.User;
import com.nart.service.LoginService;
import com.nart.service.UserService;
import com.nart.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Result login(String uname, String pwd) {
        if (StringUtils.isBlank(uname) || StringUtils.isBlank(pwd)) {
            return Result.fail(ErrorCode.PARAMS_ERROR);
        }
        String password = EncryptUtil.encryptPwd(pwd);
        User user = userService.findUser(uname, password);
        if (user == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_MATCH);
        }
        String token = EncryptUtil.createToken(Long.parseLong(user.getId()));

        redisTemplate.opsForValue().set("TOKEN_" + token, GsonFormatter.toJsonString(user), 1, TimeUnit.DAYS);

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
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        User user = GsonFormatter.fromJsonToObj(userJson, User.class);
        return user;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        userService.logout(UserThreadLocal.get().getId());
        return Result.success(null);
    }

    @Override
    public Result register(String email, String uname, String pwd) {
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

        redisTemplate.opsForValue().set("TOKEN_" + token, GsonFormatter.toJsonString(user1), 1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
