package com.example.boot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wuxiaoxiao
 * @version 1.0
 * @date 2023/6/30
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusLog {
    /**
     * 功能名称
     * @return
     */
    String value() default "";
}