package cn.leta.common.annotation;

import cn.leta.common.enums.SensitiveType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 脱敏字段注解
 * Created by xiegengcai on 2018/6/29.
 *
 * @author Xie Gengcai
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveInfo {
    SensitiveType type();
}
