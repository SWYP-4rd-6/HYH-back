package io.hyh.hyhapplication.weather.infra.apiclient.air;

import io.hyh.hyhapplication.weather.infra.apiclient.dto.CommonResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class AirPollutionInfoApiClient {

    private final RestClient client;

    public AirPollutionInfoApiClient(RestClient.Builder airPollutionInfoApiClientBuilder) {
        this.client = airPollutionInfoApiClientBuilder.build();
    }


    // 시도별 실시간 측정정보 조회
    public CommonResponse<RealtimeAirQualityItem> getRealtimeCityAirQuality(AirPollutionRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getCtprvnRltmMesureDnsty")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("returnType", request.returnType())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("sidoName", URLEncoder.encode(request.sidoName(), StandardCharsets.UTF_8))
                        .queryParam("ver", "1.0") // 고정
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CommonResponse<RealtimeAirQualityItem>>() {
                })
                .getBody();
    }

    // 미세먼지 주간예보통보 조회
    public CommonResponse<WeeklyDustForecastItem> getWeeklyDustForecast(WeeklyDustForecastRequest request) {
        return client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getMinuDustWeekFrcstDspth")
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("returnType", request.returnType())
                        .queryParam("numOfRows", request.numOfRows())
                        .queryParam("pageNo", request.pageNo())
                        .queryParam("searchDate", request.searchDate())
                        .build())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CommonResponse<WeeklyDustForecastItem>>() {
                })
                .getBody();
    }

}
