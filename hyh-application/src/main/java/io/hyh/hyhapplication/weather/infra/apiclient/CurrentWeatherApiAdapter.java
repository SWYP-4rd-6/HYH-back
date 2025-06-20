package io.hyh.hyhapplication.weather.infra.apiclient;

import io.hyh.hyhapplication.weather.application.port.out.FetchCurrentWeatherPort;
import io.hyh.hyhapplication.weather.application.port.out.LoadCoordinatesPort;
import io.hyh.hyhapplication.weather.domain.*;
import io.hyh.hyhapplication.weather.infra.apiclient.response.AirPollutionApiResponse;
import io.hyh.hyhapplication.weather.infra.apiclient.response.LiveWeatherApiResponse;
import io.hyh.hyhapplication.weather.infra.apiclient.response.WeatherForecastApiResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CurrentWeatherApiAdapter implements FetchCurrentWeatherPort {

    private final LoadCoordinatesPort loadCoordinatesPort;

    private final RestClient weatherForecastRestClient;
    private final RestClient airPollutionRestClient;
    private final RestClient liveWeatherRestClient;

    private final int PAGE_NO = 1;
    private final int NUM_OF_ROWS = 60;
    private final String DATA_TYPE = "JSON";


    @Override
    @Cacheable(value = "weatherData", key = "#depth1 + '_' + #depth2 + '_' + #depth3")
    public @NotNull CurrentWeather fetchCurrentWeather(@NotNull String depth1,
                                                       @Nullable String depth2,
                                                       @Nullable String depth3) {
        var administrativeRegion = loadCoordinatesPort.getCoordinatesForAddress(depth1, depth2,
                depth3);

        var weatherForecastResponse = fetchWeatherForecast(administrativeRegion);
        var airPollutionResponse = fetchAirPollution(administrativeRegion);
        var liveWeatherResponse = fetchLiveWeather(administrativeRegion);

        return mapToDomainModel(weatherForecastResponse, airPollutionResponse, liveWeatherResponse);
    }

    private WeatherForecastApiResponse fetchWeatherForecast(
            AdministrativeRegion administrativeRegion) {
        var forecastTime = getBaseTime(LocalDateTime.now());
        return weatherForecastRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", PAGE_NO)
                        .queryParam("numOfRows", NUM_OF_ROWS)
                        .queryParam("dataType", DATA_TYPE)
                        .queryParam("base_date", forecastTime.baseDate())
                        .queryParam("base_time", forecastTime.baseTime())
                        .queryParam("nx", administrativeRegion.latitudeDegree())
                        .queryParam("ny", administrativeRegion.longitudeDegree())
                        .build())
                .retrieve()
                .toEntity(WeatherForecastApiResponse.class)
                .getBody();
    }

    private AirPollutionApiResponse fetchAirPollution(AdministrativeRegion administrativeRegion) {
        // TODO 지역 이름 변환
        Map<String, String> regionMap = Map.of(
                "서울특별시", "서울",
                "경기도", "경기"
        );
        return airPollutionRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", 1)
                        .queryParam("numOfRows", 100)
                        .queryParam("returnType", DATA_TYPE)
                        .queryParam("sidoName", regionMap.get(administrativeRegion.regionDepth1()))
                        .build())
                .retrieve()
                .toEntity(AirPollutionApiResponse.class)
                .getBody();
    }

    private LiveWeatherApiResponse fetchLiveWeather(AdministrativeRegion administrativeRegion) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        return liveWeatherRestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("serviceKey", "{serviceKey}")
                        .queryParam("pageNo", 1)
                        .queryParam("numOfRows", 100)
                        .queryParam("dataType", DATA_TYPE)
                        .queryParam("areaNo", administrativeRegion.adminCode())
                        .queryParam("time", time)
                        .build())
                .retrieve()
                .toEntity(LiveWeatherApiResponse.class)
                .getBody();
    }

    private CurrentWeather mapToDomainModel(WeatherForecastApiResponse weatherForecastResponse,
                                            AirPollutionApiResponse airPollutionResponse,
                                            LiveWeatherApiResponse liveWeatherResponse) {

        return new CurrentWeather(
                WeatherCondition.from(
                        weatherForecastResponse.getItemsByCategory("SKY").getFirst(),
                        weatherForecastResponse.getItemsByCategory("PTY").getFirst(),
                        weatherForecastResponse.getItemsByCategory("LGT").getFirst()
                ),
                HumidityLevel.fromValue(
                        weatherForecastResponse.getItemsByCategory("REH").getFirst()
                ),
                RainfallIntensity.fromValue(
                        weatherForecastResponse.getItemsByCategory("RN1").getFirst()
                ),
                AirQuality.Pm10.fromValue(
                        airPollutionResponse.getPm10()
                ),

                AirQuality.Pm25.fromValue(
                        airPollutionResponse.getPm25()
                ),
                UvIndex.fromValue(
                        liveWeatherResponse.getCurrentUVIndex()
                )
        );
    }


    record BaseTime(String baseDate /*yyyyMMdd*/, String baseTime /*HHmm*/) {

    }

    /**
     * 주어진 시간 기준으로 가장 최신 예보 시간을 계산
     */
    private BaseTime getBaseTime(LocalDateTime baseLocalDateTime) {
        List<String> FORECAST_TIMES = List.of("0200", "0500", "0800", "1100", "1400", "1700",
                "2000", "2300");

        String currentTime = baseLocalDateTime.format(DateTimeFormatter.ofPattern("HHmm"));
        String today = baseLocalDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yesterday = baseLocalDateTime.minusDays(1)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String latestTime = null;
        for (String forecastTime : FORECAST_TIMES) {
            if (forecastTime.compareTo(currentTime) <= 0) {
                latestTime = forecastTime;
            } else {
                break;
            }
        }

        if (latestTime == null) {
            return new BaseTime(yesterday, FORECAST_TIMES.getLast());
        }

        return new BaseTime(today, latestTime);
    }

}
