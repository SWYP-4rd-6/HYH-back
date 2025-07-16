package io.hyh.hyhapplication.auth.infra.client;

import io.hyh.hyhapplication.auth.infra.provider.kakao.KakaoProperties;
import io.hyh.hyhapplication.common.apiclient.ClientLoggerRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@RequiredArgsConstructor
public class AuthRestClientConfig {

    private final KakaoProperties kakaoProperties;

    @Bean
    RestClient kakaoRestClient(ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        return RestClient.builder()
                .baseUrl(kakaoProperties.getBaseUrl())
                .requestInterceptor(clientLoggerRequestInterceptor)
                .build();
    }

}
