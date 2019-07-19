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

    public final static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJjhNqqD65v24yRD\n" +
            "cmeH9T+3ujV3u32iASNM6yrYILaESygVM5dLfuDiSSyd+5TG7p09oUXwcMUqF2sd\n" +
            "9r9DLkgnoiREZ8OyaulelBaztJWK7OGvtX9ZoOao5Zf6K0/h9hs60lnaq3RIh5uC\n" +
            "k41+eE6T7Hwf3foxVBRjt8eP1e0pAgMBAAECgYAQWZ3AFMXA82GNgk0c93RqMRsQ\n" +
            "FtMNd6MJhHNuLJkrC/EBw2q2FpPmNy0+QVMjsTbZyDqUQUe+PmcsxJKcF/I+CPWT\n" +
            "5HK+cm+867Rq0V3S3MHk3Ylao6UWeBYH81PXVHSvdL+ffYcZ19VynWQF2saVTzOZ\n" +
            "6b3f/bxzMQ4op/FwkQJBAMgW1y2jj6+Vm+H4wTogkUT2IzTDs1apbIhX5G12g2VY\n" +
            "EPY21ik9CZ8K/bOxfFviN8LnWAWGsosY8NtQNXny2q0CQQDDmUugg3L5AFobqJK7\n" +
            "/iYALKr/OmaxWDCPfyBz1zejg+2nSzA6qy4IqK9h3uq3/ezSOokd9vAmoorOOf+n\n" +
            "ucftAkEAmyg7DAT9cubMzlobXHvMqnFDs7Ld6qHOq5ucYtW35mp4HLVBNRFD2LSp\n" +
            "LyK+kHHHU7gZpY9h/EMG4so77ajIsQJALvGuvfJueKaF+ddmiCoCEs6lp6Xrmaqx\n" +
            "1O7M6iCAIQV710frZllJLhjWJuYqbUhLO1WDLSbkElG2gLJY0nd7LQJAcex9rcC+\n" +
            "pra6KcfaycrbqoqvztGIAeRNDf05W1SPUaK7f9X1fknxbxPujXJxfFX/7cRMV24l\n" +
            "4yYIfjMIxOYQBg==";
    private HttpHeaders headers;

    private InputStream body;

    public HttpMessage(HttpInputMessage inputMessage) throws IOException {
        this.headers = inputMessage.getHeaders();
        InputStream body = inputMessage.getBody();
        String s = IOUtils.toString(body, "utf-8");
        String content = easpString(s);
        this.body = IOUtils.toInputStream(RSAUtils.decryptDataOnJava(content, PRIVATE_KEY), "utf-8");
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
