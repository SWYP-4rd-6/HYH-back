package io.hyh.hyhapplication.weather.application.port.in.dto;

import lombok.Builder;
import org.jetbrains.annotations.Nullable;

@Builder
public record GetTodayWeatherResult(
        String currentTemperature,
        String maxTemperature,
        String minTemperature,
        @Nullable WeatherDto morning,
        @Nullable WeatherDto afternoon,
        @Nullable WeatherDto evening,
        int nx,
        int ny,
        String baseDateTime // 관측시간 yyyyMMddHHmm
) {

    @Builder
    public record WeatherDto(
            String fcstDateTime, // 예측시간 yyyyMMddHHmm
            String temperature,
            String sky
    ) {
    }

}
