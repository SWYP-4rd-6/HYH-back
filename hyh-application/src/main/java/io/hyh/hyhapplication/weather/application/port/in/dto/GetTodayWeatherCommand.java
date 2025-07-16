package io.hyh.hyhapplication.weather.application.port.in.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record GetTodayWeatherCommand(
        @NotNull String baseDate, // yyyyMMdd
        @NotNull String baseTime,
        @NotNull String depth1,
        @Nullable String depth2,
        @Nullable String depth3
) {
}
