package com.nart.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: EncryptUtil
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/8/30 16:26
 */
public class EncryptUtil {
    private static String salt = "3251637nart%^&";

    public static String encryptPwd(String pwd) {
        return DigestUtils.md5Hex(pwd + salt);
    }
}
