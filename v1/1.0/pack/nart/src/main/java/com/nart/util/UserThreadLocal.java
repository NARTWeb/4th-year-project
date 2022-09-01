package com.nart.util;

import com.nart.pojo.user;

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

    private static final ThreadLocal<user> LOCAL = new ThreadLocal<>();

    public static void put(user user) {
        LOCAL.set(user);
    }
    public static user get() {
        return LOCAL.get();
    }
    public static void remove() {
        LOCAL.remove();
    }
}
