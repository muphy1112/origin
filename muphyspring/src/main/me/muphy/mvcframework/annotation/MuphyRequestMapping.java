package me.muphy.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * 2019/3/25
 * 莫非
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MuphyRequestMapping {
    String value() default "";
//    String[] params() default {};
}
