package com.example.hyh.weather.adapter.out.api;

import com.example.hyh.global.ClientLoggerRequestInterceptor;
import com.example.hyh.weather.adapter.out.api.properties.AirPollutionApiProperties;
import com.example.hyh.weather.adapter.out.api.properties.LiveWeatherApiProperties;
import com.example.hyh.weather.adapter.out.api.properties.WeatherForecastApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WeatherRestClientConfig {

    private final WeatherForecastApiProperties weatherForecastApiProperties;
    private final AirPollutionApiProperties airPollutionApiProperties;
    private final LiveWeatherApiProperties liveWeatherApiProperties;

    @Bean
    public RestClient weatherForecastRestClient(RestClient.Builder builder, ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(weatherForecastApiProperties.getBaseUrl());
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        uriBuilderFactory.setDefaultUriVariables(Map.of("serviceKey", weatherForecastApiProperties.getApiKey()));
        return builder
                .uriBuilderFactory(uriBuilderFactory)
                .requestInterceptor(clientLoggerRequestInterceptor)
                .build();
    }

    @Bean
    public RestClient airPollutionRestClient(RestClient.Builder builder, ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(airPollutionApiProperties.getBaseUrl());
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        uriBuilderFactory.setDefaultUriVariables(Map.of("serviceKey", airPollutionApiProperties.getApiKey()));
        return builder
                .uriBuilderFactory(uriBuilderFactory)
                .requestInterceptor(clientLoggerRequestInterceptor)
                .build();
    }

    @Bean
    public RestClient liveWeatherRestClient(RestClient.Builder builder, ClientLoggerRequestInterceptor clientLoggerRequestInterceptor) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(liveWeatherApiProperties.getBaseUrl());
        uriBuilderFactory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        uriBuilderFactory.setDefaultUriVariables(Map.of("serviceKey", liveWeatherApiProperties.getApiKey()));
        return builder
                .uriBuilderFactory(uriBuilderFactory)
                .requestInterceptor(clientLoggerRequestInterceptor)
                .build();
    }

}
