package io.hyh.hyhapplication.weather.infra.apiclient.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external.live-weather.api")
@Getter
@Setter
public class LiveWeatherApiProperties {

    private String baseUrl;
    private String apiKey;
}
