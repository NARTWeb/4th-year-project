package com.nart.common;


import java.lang.annotation.*;

/**
 * 日志注解
 */
// Type 代表可以放在类上， method代表可以放在方法上
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogA {
    String module() default "";
    String operator() default "";
}
