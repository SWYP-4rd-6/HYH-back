package com.example.hyh.transit.domain.openApi;

public record RealTimeSeoulBusHeaderMessage(
        String headerCd,
        String headerMsg,
        String itemCount
) {
}
