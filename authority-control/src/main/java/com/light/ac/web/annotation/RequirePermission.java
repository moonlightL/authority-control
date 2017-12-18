package com.light.ac.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})//可添加注解的地方，此处是 类 和 方法上
@Retention(RetentionPolicy.RUNTIME)//运行时机，此处是 运行期
public @interface RequirePermission {
    String value();
}
