package me.muphy.mvcframework.anotation;

import java.lang.annotation.*;

/**
 * 2019/3/27
 * 莫非
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MuphyController {
    String value() default "";
}
