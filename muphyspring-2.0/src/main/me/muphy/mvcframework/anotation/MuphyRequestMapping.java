package me.muphy.mvcframework.anotation;

import java.lang.annotation.*;

/**
 * 2019/3/27
 * 莫非
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MuphyRequestMapping {
    String value() default "";
}
