package com.example.hyh.transit.domain.openApi;

public record RealTimeSeoulBusListAtStationHeaderMessage(
        String headerCd,
        String headerMsg,
        String itemCount
) {
}
