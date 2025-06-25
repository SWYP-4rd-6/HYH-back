package io.hyh.hyhapplication.weather.infra.apiclient.air;

public record RealtimeAirQualityItem(
        String stationName,
        String sidoName,
        String dataTime,
        String so2Value,
        String coValue,
        String o3Value,
        String no2Value,
        String pm10Value,
        String pm25Value,
        String khaiValue,
        String so2Grade,
        String coGrade,
        String o3Grade,
        String no2Grade,
        String pm10Grade,
        String pm25Grade,
        String khaiGrade,
        String so2Flag,
        String coFlag,
        String o3Flag,
        String no2Flag,
        String pm10Flag,
        String pm25Flag
) {
}
