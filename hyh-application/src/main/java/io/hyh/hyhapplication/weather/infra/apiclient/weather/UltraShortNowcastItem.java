package io.hyh.hyhapplication.weather.infra.apiclient.weather;

public record UltraShortNowcastItem(
        String baseDate,
        String baseTime,
        String category,
        String obsrValue,
        int nx,
        int ny
) {
}
