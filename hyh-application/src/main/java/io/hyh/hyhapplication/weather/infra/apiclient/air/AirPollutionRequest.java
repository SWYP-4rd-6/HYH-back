package io.hyh.hyhapplication.weather.infra.apiclient.air;

public record AirPollutionRequest(
        int pageNo,
        int numOfRows,
        String returnType, // json, xml
        String sidoName // 시/도 이름
) {
}
