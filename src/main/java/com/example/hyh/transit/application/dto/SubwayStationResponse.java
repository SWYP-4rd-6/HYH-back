package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.SubwayStation;

public record SubwayStationResponse(
        int subwayId,
        Long subwayStationId,
        String subwayStationName,
        String subwayStationLine
) {
    public static SubwayStationResponse of(SubwayStation subwayStation){
        return new SubwayStationResponse(
                subwayStation.getSubwayId(),
                subwayStation.getSubwayStationId(),
                subwayStation.getSubwayStationName(),
                subwayStation.getSubwayStationLine()
        );
    }
}
