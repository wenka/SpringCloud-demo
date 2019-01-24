package com.example.springcloud.jwt.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.UUID;

/**
 * Created with IDEA
 * author:wenka wkwenka@gmail.com
 * Date:2019/01/24  下午 04:38
 * Description:
 */
public class JWTUtil extends JWT {

    public static void main(String[] args) {
        //HMAC
        Algorithm algorithmHS = Algorithm.HMAC256("secret");
        String wk = JWT.create().withIssuer("wk")
                .withClaim("gender", "male")
//                .withClaim("age", 20)
                .withAudience()
                .sign(algorithmHS);
        System.out.println(wk);

        Algorithm algorithm = Algorithm.HMAC256("secret"); //com.auth0.jwt.exceptions.SignatureVerificationException
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("wk")
                .build(); //Reusable verifier instance
        // Signature : com.auth0.jwt.exceptions.SignatureVerificationException
        // Payload : com.auth0.jwt.exceptions.JWTDecodeException
        // header : com.auth0.jwt.exceptions.JWTDecodeException
        DecodedJWT jwt = verifier.verify(wk);
        System.out.println("token==>" + jwt.getToken());
        System.out.println("header==>" + jwt.getHeader());
        System.out.println("payload==>" + jwt.getPayload());
        System.out.println("signature==>" + jwt.getSignature());
        // 头部
        System.out.println("签名的算法 alg==>" + jwt.getAlgorithm());
        System.out.println("令牌类型 typ==>" + jwt.getType());
        System.out.println("签发人 iss==>" + jwt.getIssuer());
        System.out.println("过期时间 exp==>" + jwt.getExpiresAt());
        System.out.println("主题 sub==>" + jwt.getSubject());
        System.out.println("受众 aud==>" + jwt.getAudience());
        System.out.println("生效时间 nbf==>" + jwt.getNotBefore());
        System.out.println("签发时间 iat==>" + jwt.getIssuedAt());
        System.out.println("编号 id==>" + jwt.getId());
        System.out.println("age==>" + jwt.getClaim("age").asInt());

        System.out.println(UUID.randomUUID());
    }
}
