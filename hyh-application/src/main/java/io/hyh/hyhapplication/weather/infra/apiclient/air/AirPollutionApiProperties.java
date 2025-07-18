package io.hyh.hyhapplication.weather.infra.apiclient.air;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "external.air-pollution.api")
@Getter
@Setter
public class AirPollutionApiProperties {
    private String baseUrl;
    private String apiKey;
    private int connectTimeout = 5000;
}
