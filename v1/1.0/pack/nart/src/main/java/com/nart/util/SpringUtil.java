package com.nart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: SpringUtil
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022-09-29 11:07 a.m.
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    private static ApplicationContext applicationContext;

    @Override

    public void setApplicationContext(ApplicationContext applicationContext) {

        if(SpringUtil.applicationContext == null) {

            SpringUtil.applicationContext = applicationContext;

        }

        logger.info("ApplicationContext配置成功,applicationContext对象："+SpringUtil.applicationContext);

    }

    public static ApplicationContext getApplicationContext() {

        return applicationContext;

    }

    public static Object getBean(String name) {

        return getApplicationContext().getBean(name);

    }

    public static <T> T getBean(Class<T> clazz) {

        return getApplicationContext().getBean(clazz);

    }

    public static <T> T getBean(String name,Class<T> clazz) {

        return getApplicationContext().getBean(name,clazz);

    }


}