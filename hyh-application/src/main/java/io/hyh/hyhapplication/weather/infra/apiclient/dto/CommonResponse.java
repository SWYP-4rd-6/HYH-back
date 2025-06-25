package io.hyh.hyhapplication.weather.infra.apiclient.dto;

import java.util.List;

// 공공데이터 포털 공통 응답 형식
public record CommonResponse<T>(
        Response<T> response
) {
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
            int pageNo,
            int numOfRows,
            int totalCount,
            List<T> items
    ) {
    }

}
