package com.summit.constant.common.util.encryption.annotation;
import java.lang.annotation.*;
/**
 * 自定义一个注解，用来修饰方法，判断加密行为。
 * @author wu
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface EncryptFilter {

    /**
     * 对入参是否解密
     *
     * @return
     */
    boolean decryptRequest() default true;

    /**
     * 对出参是否加密
     */
    boolean encryptResponse() default true;
}