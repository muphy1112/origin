package me.muphy.mvcframework.anotation;

import java.lang.annotation.*;

/**
 * 2019/3/29
 * 莫非
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MuphyRequestParam {
    String value() default "";
}
