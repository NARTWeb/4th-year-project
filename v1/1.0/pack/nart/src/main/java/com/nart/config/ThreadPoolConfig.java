package com.nart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Copyright (c) 2008-2024: Zirui Qiao
 * Project: pack
 *
 * @className: ThreadPoolConfig
 * @Description: TODO
 * @version: v1.8.0
 * @author: ZIRUI QIAO
 * @date: 2022/12/25 15:12
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);// 设置核心线程数
        executor.setMaxPoolSize(20);// 设置最大线程数
        executor.setQueueCapacity(Integer.MAX_VALUE); // 设置队列大小
        executor.setKeepAliveSeconds(60); // 设置线程活跃时间（秒）
        executor.setThreadNamePrefix("nart-app"); // 设置线程名称前缀
        executor.setWaitForTasksToCompleteOnShutdown(true); // 等待所有任务结束后再关闭线程池
        executor.initialize(); // 初始化
        return executor;
    }
}

