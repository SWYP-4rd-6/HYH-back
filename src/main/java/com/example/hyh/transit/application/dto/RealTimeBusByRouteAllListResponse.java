package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusByRouteAllList;

public record RealTimeBusByRouteAllListResponse(
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
    public static RealTimeBusByRouteAllListResponse of(RealTimeSeoulBusByRouteAllList seoulBus) {
        return new RealTimeBusByRouteAllListResponse(seoulBus.stId(),
                seoulBus.stNm(),
                seoulBus.term(),
                seoulBus.busRouteId(),
                seoulBus.busRouteAbrv(),
                seoulBus.firstTm(),
                seoulBus.lastTm(),
                seoulBus.plainNo1(),
                seoulBus.plainNo2(),
                seoulBus.busType1(),
                seoulBus.busType2(),
                seoulBus.exps1(),
                seoulBus.exps2(),
                seoulBus.isLast1(),
                seoulBus.isLast2(),
                seoulBus.arrmsg1(),
                seoulBus.arrmsg2(),
                seoulBus.mkTm());
    }
}
