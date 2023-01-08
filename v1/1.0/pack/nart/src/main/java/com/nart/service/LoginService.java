package com.nart.service;

import com.nart.pojo.User;
import com.nart.util.Result;

import javax.servlet.http.HttpSession;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: NART
 * @className: LoginService
 * @Description: This class provides operations such as user login
 *
 * @version: v1.17.0
 * @author: YUNZHOU LIU
 * @date: 2023-1-14
 *
 */
public interface LoginService {
    /**
     * @Description: In this method, users can log in to the system
       @params: String uname, String pwd, HttpSession session
      * @return: Result
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    Result login(String uname, String pwd, HttpSession session);

    /**
     * @Description: This method checks the user's token
       @params: String token
      * @return: Result
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    User checkToken(String token);

    /**
     * @Description: This method logs out the user
       @params: String token
      * @return: Result
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    Result logout(String token);

    /**
     * @Description: This method can register users
       @params: String email, String uname, String pwd, HttpSession session
      * @return: Result
     * @Author: Yunzhou Liu
     * @Date: 2023-1-14
     */
    Result register(String email, String uname, String pwd, HttpSession session);
}
