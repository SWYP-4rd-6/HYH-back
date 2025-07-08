package io.hyh.hyhapplication.weather.infra.apiclient;

import io.hyh.hyhapplication.weather.application.port.out.FetchCurrentWeatherPort;
import io.hyh.hyhapplication.weather.application.port.out.LoadCoordinatesPort;
import io.hyh.hyhapplication.weather.domain.*;
import io.hyh.hyhapplication.weather.infra.apiclient.air.AirPollutionInfoApiClient;
import io.hyh.hyhapplication.weather.infra.apiclient.air.AirPollutionRequest;
import io.hyh.hyhapplication.weather.infra.apiclient.air.RealtimeAirQualityItem;
import io.hyh.hyhapplication.weather.infra.apiclient.dto.CommonResponse;
import io.hyh.hyhapplication.weather.infra.apiclient.dto.CommonResponseV2;
import io.hyh.hyhapplication.weather.infra.apiclient.living.LivingWeatherIndexApiClient;
import io.hyh.hyhapplication.weather.infra.apiclient.living.LivingWeatherIndexItem;
import io.hyh.hyhapplication.weather.infra.apiclient.living.LivingWeatherIndexRequest;
import io.hyh.hyhapplication.weather.infra.apiclient.weather.UltraShortForecastItem;
import io.hyh.hyhapplication.weather.infra.apiclient.weather.UltraShortNowcastItem;
import io.hyh.hyhapplication.weather.infra.apiclient.weather.VilageForecastApiClient;
import io.hyh.hyhapplication.weather.infra.apiclient.weather.VilageForecastRequest;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CurrentWeatherApiAdapter implements FetchCurrentWeatherPort {

    private final LoadCoordinatesPort loadCoordinatesPort;

    private final int PAGE_NO = 1;
    private final int NUM_OF_ROWS = 60;
    private final String DATA_TYPE = "JSON";

    private final VilageForecastApiClient vilageForecastApiClient;
    private final AirPollutionInfoApiClient airPollutionInfoApiClient;
    private final LivingWeatherIndexApiClient livingWeatherIndexApiClient;


    @Override
    @Cacheable(value = "weatherData", key = "#depth1 + '_' + #depth2 + '_' + #depth3")
    public @NotNull CurrentWeather fetchCurrentWeather(@NotNull String depth1,
                                                       @Nullable String depth2,
                                                       @Nullable String depth3) {
        var administrativeRegion = loadCoordinatesPort.getCoordinatesForAddress(depth1, depth2, depth3);

        var weatherForecastResponse = fetchWeatherForecast(administrativeRegion);
        var airPollutionResponse = fetchAirPollution(administrativeRegion);
        var liveWeatherResponse = fetchLiveWeather(administrativeRegion);

        return mapToDomainModel(weatherForecastResponse, airPollutionResponse, liveWeatherResponse);
    }

    @Override
    public @NotNull CurrentNowCast fetchCurrentNowCast(@NotNull String depth1, @Nullable String depth2, @Nullable String depth3) {
        var administrativeRegion = loadCoordinatesPort.getCoordinatesForAddress(depth1, depth2, depth3);
        var weatherNowcastResponse = fetchShortNowcase(administrativeRegion.gridX(), administrativeRegion.gridY());

        return new CurrentNowCast(
                getNowcastItemsByCategory(weatherNowcastResponse, "T1H").getFirst(),
                getNowcastItemsByCategory(weatherNowcastResponse, "RN1").getFirst(),
                getNowcastItemsByCategory(weatherNowcastResponse, "UUU").getFirst(),
                getNowcastItemsByCategory(weatherNowcastResponse, "VVV").getFirst(),
                getNowcastItemsByCategory(weatherNowcastResponse, "REH").getFirst(),
                getNowcastItemsByCategory(weatherNowcastResponse, "PTY").getFirst(),
                getNowcastItemsByCategory(weatherNowcastResponse, "VEC").getFirst(),
                getNowcastItemsByCategory(weatherNowcastResponse, "WSD").getFirst()
        );
    }

    private CommonResponseV2<UltraShortNowcastItem> fetchShortNowcase(int nx, int ny) {
        var baseTime = BaseTimeRequest.fieMinuteAgo(LocalDateTime.now()); // 5분전
        return vilageForecastApiClient.getUltraShortNowcast(
                new VilageForecastRequest(
                        PAGE_NO, 8, DATA_TYPE,
                        baseTime.baseDate(),
                        baseTime.baseTime(),
                        nx, ny
                )
        );
    }

    private CommonResponseV2<UltraShortForecastItem> fetchWeatherForecast(AdministrativeRegion administrativeRegion) {
        var forecastTime = BaseTimeRequest.oneHourAgo(LocalDateTime.now());
        return vilageForecastApiClient.getUltraShortForecast(new VilageForecastRequest(
                PAGE_NO, NUM_OF_ROWS, DATA_TYPE, forecastTime.baseDate(), forecastTime.baseTime(),
                administrativeRegion.latitudeDegree(), administrativeRegion.longitudeDegree()
        ));
    }

    private CommonResponse<RealtimeAirQualityItem> fetchAirPollution(AdministrativeRegion administrativeRegion) {
        // TODO 지역 이름 변환
        Map<String, String> regionMap = Map.of(
                "서울특별시", "서울",
                "경기도", "경기"
        );
        return airPollutionInfoApiClient.getRealtimeCityAirQuality(new AirPollutionRequest(
                1, 100, DATA_TYPE, regionMap.get(administrativeRegion.regionDepth1())
        ));
    }

    private CommonResponseV2<LivingWeatherIndexItem> fetchLiveWeather(AdministrativeRegion administrativeRegion) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH"));
        return livingWeatherIndexApiClient.getUvIndex(
                new LivingWeatherIndexRequest(
                        1, 100, DATA_TYPE, administrativeRegion.adminCode(), time
                )
        );
    }

    private CurrentWeather mapToDomainModel(CommonResponseV2<UltraShortForecastItem> weatherForecastResponse,
                                            CommonResponse<RealtimeAirQualityItem> airPollutionResponse,
                                            CommonResponseV2<LivingWeatherIndexItem> liveWeatherResponse) {

        return new CurrentWeather(
                WeatherCondition.from(
                        getShortForcastItemsByCategory(weatherForecastResponse, "SKY").getFirst(),
                        getShortForcastItemsByCategory(weatherForecastResponse, "PTY").getFirst(),
                        getShortForcastItemsByCategory(weatherForecastResponse, "LGT").getFirst()
                ),
                HumidityLevel.fromValue(
                        getShortForcastItemsByCategory(weatherForecastResponse, "REH").getFirst()
                ),
                RainfallIntensity.fromValue(
                        getShortForcastItemsByCategory(weatherForecastResponse, "RN1").getFirst()
                ),
                AirQuality.Pm10.fromValue(
                        airPollutionResponse.response().body().items().getFirst().pm10Value()
                ),
                AirQuality.Pm25.fromValue(
                        airPollutionResponse.response().body().items().getFirst().pm25Value()
                ),
                UvIndex.fromValue(
                        liveWeatherResponse.response().body().items().item().getFirst().h0()
                )
        );
    }

    private List<String> getShortForcastItemsByCategory(@NotNull CommonResponseV2<UltraShortForecastItem> response, @NotNull String category) {
        return response.response().body().items().item().stream()
                .filter(item -> category.equals(item.category()))
                .sorted(Comparator.comparing(item -> item.baseDate() + item.baseTime()))
                .map(UltraShortForecastItem::fcstValue)
                .sorted()
                .toList();
    }

    private List<String> getNowcastItemsByCategory(@NotNull CommonResponseV2<UltraShortNowcastItem> response, @NotNull String category) {
        return response.response().body().items().item().stream()
                .filter(item -> category.equals(item.category()))
                .map(UltraShortNowcastItem::obsrValue)
                .sorted()
                .toList();
    }

    /**
     * 주어진 시간 기준으로 가장 최신 예보 시간을 계산
     */
    private BaseTimeRequest getBaseTime(LocalDateTime baseLocalDateTime) {
        List<String> FORECAST_TIMES = List.of("0200", "0500", "0800", "1100", "1400", "1700", "2000", "2300");

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
            return new BaseTimeRequest(yesterday, FORECAST_TIMES.getLast());
        }

        return new BaseTimeRequest(today, latestTime);
    }

    record BaseTimeRequest(String baseDate /*yyyyMMdd*/, String baseTime /*HHmm*/) {

        public static BaseTimeRequest from(LocalDateTime dateTime) {
            String baseDate = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String baseTime = dateTime.format(DateTimeFormatter.ofPattern("HHmm"));

            return new BaseTimeRequest(baseDate, baseTime);
        }

        public static BaseTimeRequest fieMinuteAgo(LocalDateTime dateTime) {
            LocalDateTime fieMinuteAgo = dateTime.minusMinutes(5);
            return from(fieMinuteAgo);
        }

        public static BaseTimeRequest oneHourAgo(LocalDateTime dateTime) {
            LocalDateTime oneHourAgo = dateTime.minusHours(1);
            return from(oneHourAgo);
        }

    }

}
