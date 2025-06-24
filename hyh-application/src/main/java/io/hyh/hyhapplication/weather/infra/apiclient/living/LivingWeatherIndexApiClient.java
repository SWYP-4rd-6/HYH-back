package io.hyh.hyhapplication.weather.infra.apiclient.living;

import io.hyh.hyhapplication.weather.infra.apiclient.dto.CommonResponseV2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class LivingWeatherIndexApiClient {

    private final RestClient client;

    public LivingWeatherIndexApiClient(RestClient.Builder livingWeatherIndexRestClientBuilder) {
        this.client = livingWeatherIndexRestClientBuilder.build();
    }


    // 자외선지수 조회
    public CommonResponseV2<LivingWeatherIndexItem> getUvIndex(LivingWeatherIndexRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUVIdxV4")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("dataType", request.dataType())
                        .queryParam("areaNo", request.areaNo())
                        .queryParam("time", request.time())
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CommonResponseV2<LivingWeatherIndexItem>>() {
                })
                .getBody();
    }

    // 대기확산지수 조회
    public CommonResponseV2<LivingWeatherIndexItem> getAirDiffusionIndex(LivingWeatherIndexRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getAirDiffusionIdxV4")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("dataType", request.dataType())
                        .queryParam("areaNo", request.areaNo())
                        .queryParam("time", request.time())
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CommonResponseV2<LivingWeatherIndexItem>>() {
                })
                .getBody();
    }

}
