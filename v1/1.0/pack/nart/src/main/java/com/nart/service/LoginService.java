package com.nart.service;

import com.nart.pojo.user;
import com.nart.util.Result;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: LoginService
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/31 20:50
 */
public interface LoginService {
    Result login(String uname, String pwd);

    user checkToken(String token);

    Result logout(String token);

    Result register(String email, String uname, String pwd);
}
