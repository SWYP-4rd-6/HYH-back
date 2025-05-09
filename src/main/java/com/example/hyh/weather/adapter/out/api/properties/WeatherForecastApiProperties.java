package com.example.hyh.weather.adapter.out.api.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external.weather-forecast.api")
@Getter
@Setter
public class WeatherForecastApiProperties {
    private String baseUrl;
    private String apiKey;
}