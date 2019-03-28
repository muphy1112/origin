package me.muphy.mvcframework.anotation;

import java.lang.annotation.*;

/**
 * 2019/3/27
 * 莫非
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MuphyAutoWired {
    String value() default "";
}
