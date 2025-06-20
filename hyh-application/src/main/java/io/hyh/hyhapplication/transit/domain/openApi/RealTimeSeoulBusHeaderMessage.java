package io.hyh.hyhapplication.transit.domain.openApi;

public record RealTimeSeoulBusHeaderMessage(
        String headerCd,
        String headerMsg,
        String itemCount
) {
}
