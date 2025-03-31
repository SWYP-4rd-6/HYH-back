package com.example.hyh.global;

import com.example.hyh.transit.application.component.GyeongiBusComponent;
import com.example.hyh.transit.application.component.SeoulBusComponent;
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
    private String subwayKey;

    @Bean
    public SeoulBusComponent seoulBusRealTimeService(){
        RestClient restClient = RestClient.builder().baseUrl("http://ws.bus.go.kr/api/rest/arrive").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(SeoulBusComponent.class);
    }

    @Bean
    public GyeongiBusComponent gyeongiBusStationService(){
        RestClient restClient = RestClient.builder().baseUrl("https://apis.data.go.kr/6410000").build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(GyeongiBusComponent.class);
    }

    @Bean
    public SubwayComponent subwayRealTimeService(){
        RestClient restClient = RestClient.builder().baseUrl("http://swopenAPI.seoul.go.kr/api/subway/" + subwayKey).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(SubwayComponent.class);
    }

}
