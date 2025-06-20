package io.hyh.hyhapplication.auth.infra.client;

import io.hyh.hyhapplication.auth.infra.provider.kakao.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class AuthRestClientConfig {

    private final KakaoProperties kakaoProperties;

    @Bean
    RestClient kakaoRestClient() {
        return RestClient.builder()
                .baseUrl(kakaoProperties.getBaseUrl())
                // TODO 에러 핸들링
                .build();
    }

}
