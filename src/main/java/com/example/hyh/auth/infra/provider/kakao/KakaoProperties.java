package com.example.hyh.auth.infra.provider.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kakao")
@Getter
public class KakaoProperties {

    @Value("${kakao.base-url}")
    private String baseUrl;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.grant-type}")
    private String grantType;

}

