package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.openApi.RealTimeGyeonggiBusListAtStation;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusListAtStation;

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
        return new RealTimeBusListAtStationResponse(seoulBus.getStId(),
                seoulBus.getBusRouteId(),
                seoulBus.getBusRouteAbrv(),
                seoulBus.getPlainNo1(),
                seoulBus.getPlainNo2(),
                seoulBus.getBusType1(),
                seoulBus.getBusType2(),
                seoulBus.getExps1(),
                seoulBus.getExps2(),
                seoulBus.getArrmsg1(),
                seoulBus.getArrmsg2(),
                seoulBus.getMkTm());
    }

    public static RealTimeBusListAtStationResponse of(RealTimeGyeonggiBusListAtStation gyeonggiBus) {
        return new RealTimeBusListAtStationResponse(gyeonggiBus.getStationId(),
                gyeonggiBus.getRouteId(),
                gyeonggiBus.getRouteName(),
                gyeonggiBus.getPlateNo1(),
                gyeonggiBus.getPlateNo2(),
                gyeonggiBus.getLowPlate1(),
                gyeonggiBus.getLowPlate2(),
                gyeonggiBus.getPredictTimeSec1(),
                gyeonggiBus.getPredictTimeSec2(),
                null,
                null,
                null);
    }
}
