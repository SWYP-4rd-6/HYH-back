package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.SubwayStation;

import java.util.List;

public record SubwayStationResponse(
        int subwayId,
        Long subwayStationId,
        String subwayStationName,
        String subwayStationLine
) {
    public static SubwayStationResponse of(SubwayStation subwayStation) {
        return new SubwayStationResponse(
                subwayStation.getSubwayId(),
                subwayStation.getSubwayStationId(),
                subwayStation.getSubwayStationName(),
                subwayStation.getSubwayStationLine()
        );
    }

    public static List<SubwayStationResponse> listOf(List<SubwayStation> subwayStations) {
        return subwayStations.stream()
                .map(SubwayStationResponse::of)
                .toList();
    }

}
