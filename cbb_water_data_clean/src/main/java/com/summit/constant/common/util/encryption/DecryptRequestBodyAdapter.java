package com.summit.constant.common.util.encryption;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import com.summit.constant.common.util.encryption.annotation.EncryptFilter;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>接口入参解密</p>
 * RequestBodyAdvice可以理解为在@RequestBody之前需要进行的 操作，<br/>
 * ResponseBodyAdvice可以理解为在@ResponseBody之后进行的操作;<br/>
 * 所以当接口需要加解密时，在使用@RequestBody接收前台参数之前可以先在RequestBodyAdvice的实现类中进行参数的解密，<br/>
 * 当操作结束需要返回数据时，可以在@ResponseBody之后进入ResponseBodyAdvice的实现类中进行参数的加密。<br/>
 *
 */
@RestControllerAdvice
@Slf4j
public class DecryptRequestBodyAdapter extends RequestBodyAdviceAdapter {

    /**
     * 该方法用于判断当前请求，是否要执行beforeBodyRead方法
     *
     * @param methodParameter handler方法的参数对象
     * @param targetType      handler方法的参数类型
     * @param converterType   将会使用到的Http消息转换器类类型
     * @return 返回true则会执行beforeBodyRead
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();
        if (Objects.nonNull(method)) {
            EncryptFilter encryptFilter = method.getAnnotation(EncryptFilter.class);
            if (Objects.nonNull(encryptFilter)) {
                return encryptFilter.decryptRequest();
            }
        }
        return false;
    }

    /**
     * 在Http消息转换器执转换，之前执行
     *
     * @param inputMessage    客户端的请求数据
     * @param methodParameter handler方法的参数对象
     * @param targetType      handler方法的参数类型
     * @param converterType   将会使用到的Http消息转换器类类型
     * @return 返回 一个自定义的HttpInputMessage
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter methodParameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        // 读取加密的请求体
        InputStream body = inputMessage.getBody();
        HttpHeaders headers = inputMessage.getHeaders();
        headers.remove("Content-Length");
        String s = StreamUtil.getBodyString(body);
        log.info("解密前请求body:" + s);
        if (Strings.isNotEmpty(s)) {
            // 使用AES解密
            String bodyDec = AESUtil.decrypt(s);
            log.info("解密后请求body:" + bodyDec);
            if (Strings.isNotEmpty(bodyDec)) {
                // 使用解密后的数据，构造新的读取流
                InputStream inputStream = new ByteArrayInputStream(bodyDec.getBytes(StandardCharsets.UTF_8));
                return new HttpInputMessage() {
                    @Override
                    public HttpHeaders getHeaders() {
                        return inputMessage.getHeaders();
                    }

                    @Override
                    public InputStream getBody() {
                        return inputStream;
                    }
                };
            }
        }
        return inputMessage;
    }
}