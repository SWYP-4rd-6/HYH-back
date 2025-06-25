package io.hyh.hyhapplication.transit.domain.openApi;

public record RealTimeSeoulBusListAtStationHeaderMessage(
        String headerCd,
        String headerMsg,
        String itemCount
) {
}
