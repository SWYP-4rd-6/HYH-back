package io.hyh.hyhapplication.transit.infra.apiclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${subway-api-key}")
    private String subwayKey;

    @Value("${subway-timetable-key}")
    private String subwayTimeTableKey;

    @Bean
    public SeoulBusApiClient seoulBusRealTimeService() {
        RestClient restClient = RestClient.builder().baseUrl("http://ws.bus.go.kr/api/rest/arrive")
                .messageConverters(cc -> cc.add(new MappingJackson2XmlHttpMessageConverter())).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(SeoulBusApiClient.class);
    }

    @Bean
    public GyeonggiBusApiClient gyeongiBusStationService() {
        RestClient restClient = RestClient.builder().baseUrl("https://apis.data.go.kr/6410000")
                .build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(GyeonggiBusApiClient.class);
    }

    @Bean
    public SubwayApiClient subwayRealTimeService() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://swopenAPI.seoul.go.kr/api/subway/" + subwayKey).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(SubwayApiClient.class);
    }

    @Bean
    public SubwayTimeTableApiClient subwayTimeTableService() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://openAPI.seoul.go.kr:8088/" + subwayTimeTableKey).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(SubwayTimeTableApiClient.class);
    }

}
