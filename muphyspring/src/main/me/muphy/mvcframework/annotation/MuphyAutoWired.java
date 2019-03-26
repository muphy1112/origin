package me.muphy.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * 2019/3/25
 * 莫非
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MuphyAutoWired {
    String value() default "";
}
