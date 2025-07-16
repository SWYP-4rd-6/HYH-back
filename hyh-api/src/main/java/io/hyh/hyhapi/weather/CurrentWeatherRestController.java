package io.hyh.hyhapi.weather;

import io.hyh.hyhapplication.weather.application.port.in.GetCurrentWeatherUseCase;
import io.hyh.hyhapplication.weather.application.port.in.GetTodayWeatherUseCase;
import io.hyh.hyhapplication.weather.application.port.in.dto.GetCurrentWeatherCommand;
import io.hyh.hyhapplication.weather.application.port.in.dto.GetTodayWeatherCommand;
import io.hyh.hyhapplication.weather.application.port.in.dto.GetTodayWeatherResult;
import io.hyh.hyhapplication.weather.domain.CurrentWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class CurrentWeatherRestController {

    private final GetCurrentWeatherUseCase getCurrentWeatherUseCase;
    private final GetTodayWeatherUseCase getTodayWeatherUseCase;

    @GetMapping("/current")
    CurrentWeatherResponse getCurrentWeather(@RequestParam String depth1,
                                             @RequestParam(required = false) String depth2,
                                             @RequestParam(required = false) String depth3) {
        return CurrentWeatherResponse.fromCurrentWeather(
                getCurrentWeatherUseCase.getCurrentWeather(
                        new GetCurrentWeatherCommand(depth1, depth2, depth3))
        );
    }

    @GetMapping("/today")
    GetTodayWeatherResult getTodayWeather(@RequestParam String baseDate, // yyyyMMdd
                                          @RequestParam String baseTime,
                                          @RequestParam String depth1,
                                          @RequestParam(required = false) String depth2,
                                          @RequestParam(required = false) String depth3) {
        return getTodayWeatherUseCase.getTodayWeather(new GetTodayWeatherCommand(
                baseDate,
                baseTime,
                depth1,
                depth2,
                depth3
        ));
    }

    record CurrentWeatherResponse(
            String weatherCondition,
            String humidityLevel,
            String rainfallIntensity,
            String airQualityPm10,
            String airQualityPm25,
            String uvIndex
    ) {

        public static CurrentWeatherResponse fromCurrentWeather(CurrentWeather currentWeather) {
            return new CurrentWeatherResponse(
                    currentWeather.weatherCondition().getDisplayName(),
                    currentWeather.humidityLevel().getDisplayName(),
                    currentWeather.rainfallIntensity().getDisplayName(),
                    currentWeather.airQualityPm10().getDisplayName(),
                    currentWeather.airQualityPm25().getDisplayName(),
                    currentWeather.uvIndex().getDisplayName()
            );
        }
    }

}
