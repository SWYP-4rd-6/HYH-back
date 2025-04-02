package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.BusStation;

import java.util.List;

public record BusStationResponse(
        String nodeId,
        String arsId,
        String stationName,
        double latitude,
        double longitude
) {
    public static BusStationResponse of(BusStation busStation) {
        return new BusStationResponse(
                busStation.getNodeId(),
                busStation.getArsId(),
                busStation.getStationName(),
                busStation.getLatitude(),
                busStation.getLongitude()
        );
    }

    public static List<BusStationResponse> listOf(List<BusStation> busStations) {
        return busStations.stream()
                .map(BusStationResponse::of)
                .toList();
    }

}
