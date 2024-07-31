package com.leis.bear.annonations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedPermissions {
    String permission() default "";
}
