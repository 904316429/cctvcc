package cn.cctvcc.security.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * @description: 自定义 OpenFeign注解
 * @author: Jiang
 * @create: 2021-09-23 22:01
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableCctvFeignClients {

    String[] value() default {};

    String[] basePackages() default { "cn.cctvcc" };// 配置扫描包名, 否则自动装配不扫 api包下接口

    Class<?>[] basePackageClasses() default {};

    Class<?>[] defaultConfiguration() default {};

    Class<?>[] clients() default {};
}
