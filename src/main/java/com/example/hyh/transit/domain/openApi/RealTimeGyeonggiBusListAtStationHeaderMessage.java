package com.example.hyh.transit.domain.openApi;

public record RealTimeGyeonggiBusListAtStationHeaderMessage(
        String queryTime,
        int resultCode,
        String resultMessage
) {
}
