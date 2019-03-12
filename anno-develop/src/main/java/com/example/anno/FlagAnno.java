package com.example.anno;

import java.lang.annotation.*;

/**
 * @Author guojianfeng.
 * @Date Created in  2019/3/1
 * @Description：
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FlagAnno {
    String describ() default "";
}
