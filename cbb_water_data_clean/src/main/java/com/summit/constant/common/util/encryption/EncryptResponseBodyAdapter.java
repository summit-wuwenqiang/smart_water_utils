package com.summit.constant.common.util.encryption;
import java.lang.reflect.Method;
import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSON;
import com.summit.constant.common.util.encryption.annotation.EncryptFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>接口出参加密</p>
 * RequestBodyAdvice可以理解为在@RequestBody之前需要进行的 操作，<br/>
 * ResponseBodyAdvice可以理解为在@ResponseBody之后进行的操作;<br/>
 * 所以当接口需要加解密时，在使用@RequestBody接收前台参数之前可以先在RequestBodyAdvice的实现类中进行参数的解密，<br/>
 * 当操作结束需要返回数据时，可以在@ResponseBody之后进入ResponseBodyAdvice的实现类中进行参数的加密。<br/>
 *
 */
//@RestControllerAdvice("com.example.springbootEncrypt.controller")
// 表示com.example.springbootEncrypt.controller此包下的所有响应对象都会经过此拦截器，并对响应体加密
@RestControllerAdvice
@Slf4j
public class EncryptResponseBodyAdapter implements ResponseBodyAdvice<Object> {


    /**
     * 该方法用于判断当前请求的返回值，是否要执行beforeBodyWrite方法
     *
     * @param methodParameter handler方法的参数对象
     * @param converterType   将会使用到的Http消息转换器类类型
     * @return 返回true则会执行beforeBodyWrite
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();
        if (Objects.nonNull(method)) {
            EncryptFilter encryptFilter = method.getAnnotation(EncryptFilter.class);
            if (Objects.nonNull(encryptFilter)) {
                return encryptFilter.encryptResponse();
            }
        }
        return false;
    }

    /**
     * 在Http消息转换器执转换，之前执行
     *
     * @param body               服务端的响应数据
     * @param methodParameter    handler方法的参数对象
     * @param mediaType          响应的ContentType
     * @param converterType      将会使用到的Http消息转换器类类型
     * @param serverHttpRequest  serverHttpRequest
     * @param serverHttpResponse serverHttpResponse
     * @return 返回 一个自定义的HttpInputMessage，可以为null，表示没有任何响应
     */
    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter methodParameter, MediaType mediaType,Class<? extends HttpMessageConverter<?>> converterType,ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("处理请求地址：{} 的返回值", serverHttpRequest.getURI());
        //获取请求数据
        String srcData = JSON.toJSONString(body);
        log.info("加密前响应body={}", srcData);
        if (Objects.nonNull(body)) {
            //AES加密
            String returnStr = AESUtil.encrypt(srcData); 
            log.info("加密后响应body:" + returnStr);

            //添加 encrypt 告诉前端数据已加密
            //serverHttpResponse.getHeaders().add("encrypt", "e=a");
            return returnStr;
        }
        return body;
    }
}