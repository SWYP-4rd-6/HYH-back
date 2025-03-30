package com.example.hyh.global;

import com.example.hyh.transit.application.component.SubwayComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${real-time-subway-api}")
    private String key;

    @Bean
    public SubwayComponent subwayRealTimeService(){
        RestClient restClient = RestClient.builder().baseUrl("http://swopenAPI.seoul.go.kr/api/subway/" + key).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(SubwayComponent.class);
    }
}
