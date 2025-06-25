package io.hyh.hyhapplication.weather.infra.apiclient.air;

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
public class AirPollutionInfoApiClientConfig {

    private final AirPollutionApiProperties airPollutionApiProperties;

    @Bean
    public RestClient.Builder airPollutionInfoApiClientBuilder(ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(airPollutionApiProperties.getBaseUrl());
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(airPollutionApiProperties.getConnectTimeout());
        uriBuilderFactory.setDefaultUriVariables(Map.of("serviceKey", airPollutionApiProperties.getApiKey()));
        return RestClient.builder()
                .requestFactory(requestFactory)
                .uriBuilderFactory(uriBuilderFactory)
                .requestInterceptor(clientLoggerRequestInterceptor);
    }

}
