package com.demo.ciphertext.advice;

import com.demo.ciphertext.util.RSAUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  下午 01:22
 * Description:
 */
public class HttpMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    public HttpMessage(HttpInputMessage inputMessage,String privateKey) throws IOException {
        this.headers = inputMessage.getHeaders();
        InputStream body = inputMessage.getBody();
        String s = IOUtils.toString(body, "utf-8");
        String content = easpString(s);
        this.body = IOUtils.toInputStream(RSAUtils.decryptDataOnJava(content, privateKey), "utf-8");
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }

    /**
     * @param requestData
     * @return
     */
    public String easpString(String requestData) {
        if (requestData != null && !requestData.equals("")) {
            String s = "{\"requestData\":";
            if (!requestData.startsWith(s)) {
                throw new RuntimeException("参数【requestData】缺失异常！");
            } else {
                int closeLen = requestData.length() - 1;
                int openLen = "{\"requestData\":".length();
                String substring = requestData.substring(openLen, closeLen);
                return substring;
            }
        }
        return "";
    }
}
