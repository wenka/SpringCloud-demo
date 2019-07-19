package com.demo.ciphertext.advice;

import com.alibaba.fastjson.JSON;
import com.demo.ciphertext.config.CipherProperties;
import com.demo.ciphertext.model.Result;
import com.demo.ciphertext.util.RSAUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  下午 04:01
 * Description:
 */
@ControllerAdvice
@EnableConfigurationProperties(CipherProperties.class)
public class DecryptResponseBoydAdvice implements ResponseBodyAdvice<Result> {

    @Autowired
    private CipherProperties cipherProperties;

    private static String PUBLIC_KEY;

    @PostConstruct
    public void readPubKey() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(cipherProperties.getPublicKeyLocation());
        boolean exists = classPathResource.exists();
        if (cipherProperties.isEnable() && !exists){
            throw new FileNotFoundException(cipherProperties.getPublicKeyLocation());
        }
        InputStream inputStream = classPathResource.getInputStream();
        String key = IOUtils.toString(inputStream, "utf-8");
        PUBLIC_KEY = key.trim();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return cipherProperties.isEnable();
    }

    @Override
    public Result beforeBodyWrite(Result result, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (Objects.isNull(result)){
            return Result.success(null);
        }

        Object data = result.getData();
        if (Objects.isNull(data)){
            return result;
        }

        String jsonString = JSON.toJSONString(data);
        String encryptedDataOnJava = RSAUtils.encryptedDataOnJava(jsonString, PUBLIC_KEY);
        result.setData(encryptedDataOnJava);
        return result;
    }
}
