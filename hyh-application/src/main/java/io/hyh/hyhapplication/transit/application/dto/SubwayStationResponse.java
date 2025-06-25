package io.hyh.hyhapplication.transit.application.dto;

import io.hyh.hyhapplication.transit.domain.SubwayStation;

import java.util.List;

public record SubwayStationResponse(
        int subwayId,
        Long subwayStationId,
        String subwayStationName,
        String subwayStationLine,
        String stationCd,
        String frCode
) {
    public static SubwayStationResponse of(SubwayStation subwayStation) {
        return new SubwayStationResponse(
                subwayStation.getSubwayId(),
                subwayStation.getSubwayStationId(),
                subwayStation.getSubwayStationName(),
                subwayStation.getSubwayStationLine(),
                subwayStation.getStationCd(),
                subwayStation.getFrCode()
        );
    }

    public static List<SubwayStationResponse> listOf(List<SubwayStation> subwayStations) {
        return subwayStations.stream()
                .map(SubwayStationResponse::of)
                .toList();
    }

}
