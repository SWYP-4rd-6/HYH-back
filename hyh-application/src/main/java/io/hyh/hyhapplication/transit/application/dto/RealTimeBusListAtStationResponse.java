package io.hyh.hyhapplication.transit.application.dto;

import io.hyh.hyhapplication.transit.domain.openApi.RealTimeGyeonggiBusListAtStation;
import io.hyh.hyhapplication.transit.domain.openApi.RealTimeSeoulBusListAtStation;

public record RealTimeBusListAtStationResponse(
        int stId,
        String busRouteId,
        String busRouteName,
        String plainNo1,
        String plainNo2,
        int busType1,
        int busType2,
        int predictTimeSec1,
        int predictTimeSec2,
        String arrmsg1,
        String arrmsg2,
        String mkTm
) {
    public static RealTimeBusListAtStationResponse of(RealTimeSeoulBusListAtStation seoulBus) {
        return new RealTimeBusListAtStationResponse(seoulBus.stId(),
                seoulBus.busRouteId(),
                seoulBus.busRouteAbrv(),
                seoulBus.plainNo1(),
                seoulBus.plainNo2(),
                seoulBus.busType1(),
                seoulBus.busType2(),
                seoulBus.exps1(),
                seoulBus.exps2(),
                seoulBus.arrmsg1(),
                seoulBus.arrmsg2(),
                seoulBus.mkTm());
    }

    public static RealTimeBusListAtStationResponse of(RealTimeGyeonggiBusListAtStation gyeonggiBus) {
        return new RealTimeBusListAtStationResponse(gyeonggiBus.stationId(),
                gyeonggiBus.routeId(),
                gyeonggiBus.routeName(),
                gyeonggiBus.plateNo1(),
                gyeonggiBus.plateNo2(),
                gyeonggiBus.lowPlate1(),
                gyeonggiBus.lowPlate2(),
                gyeonggiBus.predictTimeSec1(),
                gyeonggiBus.predictTimeSec2(),
                null,
                null,
                null);
    }
}
