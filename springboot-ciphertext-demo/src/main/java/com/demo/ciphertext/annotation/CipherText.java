package com.demo.ciphertext.annotation;

import java.lang.annotation.*;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  上午 11:29
 * Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CipherText {

    /**
     * 入参是否加密
     *
     * @return
     */
    boolean requst() default true;

    /**
     * 出参是否加密
     *
     * @return
     */
    boolean response() default false;
}
