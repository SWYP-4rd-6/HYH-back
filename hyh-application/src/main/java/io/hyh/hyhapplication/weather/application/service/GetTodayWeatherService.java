package io.hyh.hyhapplication.weather.application.service;

import io.hyh.hyhapplication.weather.application.port.in.GetTodayWeatherUseCase;
import io.hyh.hyhapplication.weather.application.port.in.dto.GetTodayWeatherCommand;
import io.hyh.hyhapplication.weather.application.port.in.dto.GetTodayWeatherResult;
import io.hyh.hyhapplication.weather.application.port.out.FetchCurrentWeatherPort;
import io.hyh.hyhapplication.weather.application.port.out.LoadCoordinatesPort;
import io.hyh.hyhapplication.weather.domain.AdministrativeRegion;
import io.hyh.hyhapplication.weather.domain.CurrentNowCast;
import io.hyh.hyhapplication.weather.domain.WeatherData;
import io.hyh.hyhapplication.weather.domain.WeatherDataRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
class GetTodayWeatherService implements GetTodayWeatherUseCase {

    private final WeatherDataRepository weatherDataRepository;
    private final LoadCoordinatesPort loadCoordinatesPort;
    private final FetchCurrentWeatherPort fetchCurrentWeatherPort;

    @Override
    public @NotNull GetTodayWeatherResult getTodayWeather(@NotNull GetTodayWeatherCommand request) {
        AdministrativeRegion region = loadCoordinatesPort.getCoordinatesForAddress(request.depth1(), request.depth2(), request.depth3());

        List<WeatherData> weatherDataList = weatherDataRepository.getWeatherData(
                request.baseDate(),
                List.of("0800", "1200", "1800"),
                List.of("TMP", "SKY"), // 기온, 하늘상태
                region.gridX(), region.gridY()
        );
        Map<String, List<WeatherData>> timeGroupedData = weatherDataList.stream()
                .collect(Collectors.groupingBy(WeatherData::getFcstTime));

        List<WeatherData> minMaxTemperatures = weatherDataRepository.getWeatherData(
                request.baseDate(),
                List.of("TMN", "TMX"),
                region.gridX(), region.gridY()
        );

        CurrentNowCast currentNowcast = fetchCurrentWeatherPort.fetchCurrentNowCast(request.depth1(), request.depth2(), request.depth3());

        return GetTodayWeatherResult.builder()
                .currentTemperature(currentNowcast.temperature())
                .maxTemperature(findValueByCategory(minMaxTemperatures, "TMX"))
                .minTemperature(findValueByCategory(minMaxTemperatures, "TMN"))
                .morning(createWeatherDto(timeGroupedData.get("0800")))
                .afternoon(createWeatherDto(timeGroupedData.get("1200")))
                .evening(createWeatherDto(timeGroupedData.get("1800")))
                .nx(region.gridX())
                .ny(region.gridY())
                .baseDateTime(weatherDataList.getFirst().getBaseDate() + weatherDataList.getFirst().getBaseTime())
                .build();
    }

    private GetTodayWeatherResult.WeatherDto createWeatherDto(List<WeatherData> timeData) {
        if (timeData == null) return null;

        String temperature = findValueByCategory(timeData, "TMP");
        String sky = findValueByCategory(timeData, "SKY");
        String fcstDateTime = timeData.get(0).getFcstDate() + timeData.get(0).getFcstTime();

        return GetTodayWeatherResult.WeatherDto.builder()
                .fcstDateTime(fcstDateTime)
                .temperature(temperature)
                .sky(sky)
                .build();
    }

    private String findValueByCategory(List<WeatherData> data, String category) {
        return data.stream()
                .filter(d -> category.equals(d.getCategory()))
                .map(WeatherData::getFcstValue)
                .findFirst()
                .orElse(null);
    }

    record BaseTime(String baseDate /*yyyyMMdd*/, String baseTime /*HHmm*/) {
    }

    /**
     * 주어진 시간 기준으로 가장 최신 예보 시간을 계산
     */
    private BaseTime getBaseTime(LocalDateTime baseLocalDateTime) {
        List<String> FORECAST_TIMES = List.of("0200", "0500", "0800", "1100", "1400", "1700", "2000", "2300");

        String currentTime = baseLocalDateTime.format(DateTimeFormatter.ofPattern("HHmm"));
        String today = baseLocalDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String yesterday = baseLocalDateTime.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

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
