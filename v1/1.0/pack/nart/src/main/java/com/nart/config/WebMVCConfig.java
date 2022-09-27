package com.nart.config;

import com.nart.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: WebMVCConfig
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/9/1 13:05
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //跨域配置，不可设置为*，不安全, 前后端分离项目，可能域名不一致
        //本地测试 端口不一致 也算跨域
        registry.addMapping("/**")
                .allowedOrigins("*","http://localhost:8888","http://localhost:5173", "https://s3.amazonaws.com")
                .allowedMethods("PUT", "DELETE", "POST", "GET");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截的接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
    }
}
