package io.hyh.hyhapplication.weather.infra.apiclient.weather;

import lombok.Builder;

@Builder
public record VilageForecastRequest(
        int pageNo,
        int numOfRows,
        String dataType,
        String baseDate,
        String baseTime,
        int nx,
        int ny
) {
}
