package me.muphy.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * 2019/3/25
 * 莫非
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MuphyService {
    String value() default "";
}
