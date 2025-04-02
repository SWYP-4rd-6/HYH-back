package com.example.hyh.transit.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GyeonggiBusRealTimeStationResponse(
        String predictTimeSec1,
        String predictTimeSec2,
        String crowded1,
        String crowded2,
        String flag,
        String locationNo1,
        String locationNo2,
        String lowPlate1,
        String lowPlate2,
        String plateNo1,
        String plateNo2,
        String predictTime1,
        String predictTime2,
        String remainSeatCnt1,
        String remainSeatCnt2,
        String routeDestId,
        String routeDestName,
        String routeId,
        String routeName,
        String routeTypeCd,
        String staOrder,
        String stationId,
        String stationNm1,
        String stationNm2,
        String taglessCd1,
        String taglessCd2,
        String turnSeq,
        String vehId1,
        String vehId2
) {
}
