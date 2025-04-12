package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusByRouteAllList;

public record RealTimeBusByRouteAllList(
        int stId,
        String stNm,
        int term,
        String busRouteId,
        String busRouteName,
        String firstTm,
        String lastTm,
        String plainNo1,
        String plainNo2,
        int busType1,
        int busType2,
        int predictTimeSec1,
        int predictTimeSec2,
        int isLast1,
        int isLast2,
        String arrmsg1,
        String arrmsg2,
        String mkTm
) {
    public static RealTimeBusByRouteAllList of(RealTimeSeoulBusByRouteAllList seoulBus) {
        return new RealTimeBusByRouteAllList(seoulBus.getStId(),
                seoulBus.getStNm(),
                seoulBus.getTerm(),
                seoulBus.getBusRouteId(),
                seoulBus.getBusRouteAbrv(),
                seoulBus.getFirstTm(),
                seoulBus.getLastTm(),
                seoulBus.getPlainNo1(),
                seoulBus.getPlainNo2(),
                seoulBus.getBusType1(),
                seoulBus.getBusType2(),
                seoulBus.getExps1(),
                seoulBus.getExps2(),
                seoulBus.getIsLast1(),
                seoulBus.getIsLast2(),
                seoulBus.getArrmsg1(),
                seoulBus.getArrmsg2(),
                seoulBus.getMkTm());
    }
}
