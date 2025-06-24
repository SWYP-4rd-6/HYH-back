package io.hyh.hyhapplication.weather.infra.apiclient.living;

public record LivingWeatherIndexRequest(
        int pageNo,
        int numOfRows,
        String dataType, // json, xml
        String areaNo,
        String time // yyyyMMddHHmm
) {
}
