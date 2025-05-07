package com.example.hyh.weather.adapter.out.api.response;

import java.util.List;

public record AirPollutionApiResponse(
        Response<Item> response
) {

    public String getPm10() {
        return response.body.items.getFirst().pm10Value;
    }

    public String getPm25() {
        return response.body.items.getFirst().pm25Value;
    }

    public record Response<T>(
            Header header,
            Body<T> body
    ) {
    }

    public record Header(
            String resultCode,
            String resultMsg
    ) {
    }

    public record Body<T>(
            int totalCount,
            int pageNo,
            int numOfRows,
            List<T> items
    ) {
    }

    public record Item(
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
}
