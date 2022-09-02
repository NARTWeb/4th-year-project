package com.nart.util;

import com.nart.pojo.User;

import java.util.Date;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: blog-parent
 *
 * @className: UserThreadLocal
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/7/25 22:49
 */
public class UserThreadLocal {
    private UserThreadLocal(){}

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User user) {
        LOCAL.set(user);
    }
    public static User get() {
        return LOCAL.get();
    }
    public static void remove() {
        LOCAL.remove();
    }

    public static void main(String[] args) {
        System.out.println(new Date().getTime());
    }
}
