package io.hyh.hyhapplication.weather.infra.apiclient.living;

import io.hyh.hyhapplication.common.apiclient.ClientLoggerRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class LivingWeatherIndexApiClientConfig {

    private final LiveWeatherApiProperties liveWeatherApiProperties;

    @Bean
    public RestClient.Builder livingWeatherIndexRestClientBuilder(ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(liveWeatherApiProperties.getBaseUrl());
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(liveWeatherApiProperties.getConnectTimeout());
        uriBuilderFactory.setDefaultUriVariables(Map.of("serviceKey", liveWeatherApiProperties.getApiKey()));
        return RestClient.builder()
                .requestFactory(requestFactory)
                .uriBuilderFactory(uriBuilderFactory)
                .requestInterceptor(clientLoggerRequestInterceptor);
    }

}
