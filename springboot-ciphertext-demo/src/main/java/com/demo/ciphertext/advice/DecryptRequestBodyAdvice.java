package com.demo.ciphertext.advice;

import com.demo.ciphertext.annotation.CipherText;
import com.demo.ciphertext.config.CipherProperties;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  下午 01:18
 * Description: 针对所有以@RequestBody的参数，在读取请求body之前或者在body转换成对象之前可以做相应的增强
 */
@ControllerAdvice
@EnableConfigurationProperties(CipherProperties.class)
public class DecryptRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    private CipherProperties cipherProperties;

    private final static Logger LOGGER = LoggerFactory.getLogger(DecryptRequestBodyAdvice.class);
    private static String PRIVATE_KEY;

    @PostConstruct
    public void readPubKey() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(cipherProperties.getPrivateKeyLocation());
        boolean exists = classPathResource.exists();
        if (cipherProperties.isEnable() && !exists){
            throw new FileNotFoundException(cipherProperties.getPrivateKeyLocation());
        }
        InputStream inputStream = classPathResource.getInputStream();
        String key = IOUtils.toString(inputStream, "utf-8");
        PRIVATE_KEY = key.trim();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return methodParameter.getMethod().isAnnotationPresent(CipherText.class);
        return cipherProperties.isEnable();
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        CipherText cipherText = methodParameter.getMethodAnnotation(CipherText.class);
        if (cipherText != null && cipherText.requst()) {
            LOGGER.info("{} 需要进行解密操作", methodParameter.getMethod().getName());
            return new HttpMessage(httpInputMessage,PRIVATE_KEY);
        }

        if (cipherText != null && !cipherText.requst()) {
            LOGGER.info("{} 不需要进行解密操作", methodParameter.getMethod().getName());
            return httpInputMessage;
        }
        return new HttpMessage(httpInputMessage,PRIVATE_KEY);
    }

    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }
}
