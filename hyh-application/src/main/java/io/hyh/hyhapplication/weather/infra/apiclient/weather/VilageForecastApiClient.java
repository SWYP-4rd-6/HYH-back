package io.hyh.hyhapplication.weather.infra.apiclient.weather;

import io.hyh.hyhapplication.weather.infra.apiclient.dto.CommonResponseV2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class VilageForecastApiClient {

    private final RestClient client;

    public VilageForecastApiClient(RestClient.Builder vilageForecastRestClientBuilder) {
        this.client = vilageForecastRestClientBuilder.build();
    }


    // 초단기실황조회
    public CommonResponseV2<UltraShortNowcastItem> getUltraShortNowcast(VilageForecastRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUltraSrtNcst")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("dataType", request.dataType())
                        .queryParam("base_date", request.baseDate())
                        .queryParam("base_time", request.baseTime())
                        .queryParam("nx", request.nx())
                        .queryParam("ny", request.ny())
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CommonResponseV2<UltraShortNowcastItem>>() {
                })
                .getBody();
    }

    // 동네예보조회
    public CommonResponseV2<VillageForecastItem> getVillageForecast(VilageForecastRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getVilageFcst")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("dataType", request.dataType())
                        .queryParam("base_date", request.baseDate())
                        .queryParam("base_time", request.baseTime())
                        .queryParam("nx", request.nx())
                        .queryParam("ny", request.ny())
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CommonResponseV2<VillageForecastItem>>() {
                })
                .getBody();
    }

    // 초단기예보조회
    public CommonResponseV2<UltraShortForecastItem> getUltraShortForecast(VilageForecastRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUltraSrtFcst")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("dataType", request.dataType())
                        .queryParam("base_date", request.baseDate())
                        .queryParam("base_time", request.baseTime())
                        .queryParam("nx", request.nx())
                        .queryParam("ny", request.ny())
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CommonResponseV2<UltraShortForecastItem>>() {
                })
                .getBody();
    }

}
