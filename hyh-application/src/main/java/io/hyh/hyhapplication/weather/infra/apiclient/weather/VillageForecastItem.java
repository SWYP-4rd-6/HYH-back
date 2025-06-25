package io.hyh.hyhapplication.weather.infra.apiclient.weather;

public record VillageForecastItem(
        String baseDate,
        String baseTime,
        String category,
        String fcstDate,
        String fcstTime,
        String fcstValue,
        int nx,
        int ny
) {
}
