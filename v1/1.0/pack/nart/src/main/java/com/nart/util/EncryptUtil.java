package com.nart.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private static final String salt = "2361578nart!@#";
    private static final String jwtToken = "123456Nart!@#$$";

    public static String encryptPwd(String pwd) {
        return DigestUtils.md5Hex(pwd + salt);
    }

    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) // assign token, key is jwtToken
                .setClaims(claims) // use userID as body data
                .setIssuedAt(new Date()) // set create time
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));// one day available
        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String, Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        String token = EncryptUtil.createToken(100L);
        System.out.println(token);
        Map<String, Object> map = EncryptUtil.checkToken(token);
        System.out.println(map.get("userId"));
    }
}
