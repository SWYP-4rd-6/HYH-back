package io.hyh.hyhapplication.weather.infra.apiclient.weather;

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
public class VilageForecastApiClientConfig {

    private final WeatherForecastApiProperties weatherForecastApiProperties;

    @Bean
    public RestClient.Builder vilageForecastRestClientBuilder(ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(weatherForecastApiProperties.getBaseUrl());
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(weatherForecastApiProperties.getConnectTimeout());
        uriBuilderFactory.setDefaultUriVariables(Map.of("serviceKey", weatherForecastApiProperties.getApiKey()));
        return RestClient.builder()
                .requestFactory(requestFactory)
                .uriBuilderFactory(uriBuilderFactory)
                .requestInterceptor(clientLoggerRequestInterceptor);
    }

}
