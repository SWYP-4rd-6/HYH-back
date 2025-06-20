package io.hyh.hyhapplication.transit.domain.openApi;

public record RealTimeGyeonggiBusListAtStationHeaderMessage(
        String queryTime,
        int resultCode,
        String resultMessage
) {
}
