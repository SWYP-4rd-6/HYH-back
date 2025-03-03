package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.BusStation;
import com.example.hyh.transit.domain.BusStationType;

public record BusStationResponse(
        String nodeId,
        String arsId,
        String stationName,
        double latitude,
        double longitude,
        BusStationType stationType
) {
    public static BusStationResponse of(BusStation busStation) {
        return new BusStationResponse(
                busStation.getNodeId(),
                busStation.getArsId(),
                busStation.getStationName(),
                busStation.getLatitude(),
                busStation.getLongitude(),
                busStation.getStationType()
        );
    }
}
