package io.hyh.hyhapplication.weather.infra.apiclient.dto;

import java.util.List;

// 주의: items. item이 중첩됨
public record CommonResponseV2<T>(
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
            String dataType,
            int pageNo,
            int numOfRows,
            int totalCount,
            Items<T> items
    ) {
    }

    public record Items<T>(
            List<T> item
    ) {
    }

}
