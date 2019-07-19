package com.demo.ciphertext.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/07/19  下午 04:16
 * Description:
 */
@Component
@ConfigurationProperties(prefix = "wk.cipher")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class CipherProperties {

    /**
     * 是否开启
     */
    private boolean enable;

    /**
     * 加密公钥
     */
    private String publicKeyLocation;

    /**
     * 解密私钥
     */
    private String privateKeyLocation;
}
