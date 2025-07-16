package io.hyh.hyhapplication.weather.application.port.in.dto;

public record GetCurrentWeatherCommand(
        String depth1,
        String depth2,
        String depth3
) {
}
