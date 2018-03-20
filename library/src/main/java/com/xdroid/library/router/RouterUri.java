package com.xdroid.library.router;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * URI注解
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface RouterUri {
    String routerUri() default "";
}
