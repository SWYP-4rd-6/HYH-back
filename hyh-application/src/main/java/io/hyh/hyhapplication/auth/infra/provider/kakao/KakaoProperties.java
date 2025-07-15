package io.hyh.hyhapplication.auth.infra.provider.kakao;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kakao")
@Getter
@Setter
public class KakaoProperties {

    private String baseUrl;

    private String clientId;

    private String redirectUrl;

    private String clientSecret;

    private String grantType;

    private int connectTimeout = 5000;

}

