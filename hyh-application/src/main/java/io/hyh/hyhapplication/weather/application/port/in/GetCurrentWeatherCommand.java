package io.hyh.hyhapplication.weather.application.port.in;

public record GetCurrentWeatherCommand(
        String depth1,
        String depth2,
        String depth3
) {
}
