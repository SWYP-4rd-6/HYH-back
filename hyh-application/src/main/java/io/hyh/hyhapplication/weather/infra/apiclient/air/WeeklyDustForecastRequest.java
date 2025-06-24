package io.hyh.hyhapplication.weather.infra.apiclient.air;

public record WeeklyDustForecastRequest(
        int pageNo,
        int numOfRows,
        String returnType,
        String searchDate // yyyy-MM-dd
) {
}
