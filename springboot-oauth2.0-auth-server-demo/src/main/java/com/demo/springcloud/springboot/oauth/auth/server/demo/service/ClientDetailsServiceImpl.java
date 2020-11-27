package com.demo.springcloud.springboot.oauth.auth.server.demo.service;

import com.demo.springcloud.springboot.oauth.auth.server.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author wenka wkwenka@gmail.com
 * @date 2020/11/26  下午 02:30
 * @description:
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    private static final List<Client> CLIENT_LIST = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Client client1 = new Client().setId("1").setClientId("client-A").setClientSecret(this.passwordEncoder.encode("abc")).setAuthorizedGrantTypes(new HashSet<>(Arrays.asList("authorization_code","password")));
        Client client2 = new Client().setId("2").setClientId("client-B").setClientSecret(this.passwordEncoder.encode("xyz")).setAuthorizedGrantTypes(new HashSet<>(Arrays.asList("authorization_code","password")));
        CLIENT_LIST.add(client1);
        CLIENT_LIST.add(client2);
    }

    /**
     * Load a client by the client id. This method must not return null.
     *
     * @param clientId The client id.
     * @return The client details (never null).
     * @throws ClientRegistrationException If the client account is locked, expired, disabled, or invalid for any other reason.
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client c = CLIENT_LIST.stream().filter(client -> client.getClientId().equals(clientId)).findFirst().orElse(null);
        if (c == null) {
            throw new RuntimeException("【" + clientId + "】不存在！");
        }
        return c;
    }
}
