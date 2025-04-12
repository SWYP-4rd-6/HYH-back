package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.BusStation;
import com.example.hyh.transit.domain.openApi.GyeonggiBusStation;

import java.util.List;
import java.util.stream.Collectors;

public record BusStationResponse(
        String nodeId,
        String stId,
        String stationName,
        double latitude,
        double longitude
) {
    public static BusStationResponse of(BusStation busStation) {
        return new BusStationResponse(
                busStation.getNodeId(),
                busStation.getStId(),
                busStation.getStationName(),
                busStation.getLatitude(),
                busStation.getLongitude()
        );
    }

    public static BusStationResponse of(GyeonggiBusStation busStation) {
        return new BusStationResponse(
                busStation.getMobileNo(),
                busStation.getStationId(),
                busStation.getStationName(),
                busStation.getX(),
                busStation.getY()
        );
    }

    public static List<BusStationResponse> listOf(List<BusStation> busStations, List<BusStationResponse> gyeonggiBusStations) {
        List<BusStationResponse> busStationResponses = busStations.stream()
                .map(BusStationResponse::of)
                .collect(Collectors.toList());
        busStationResponses.addAll(gyeonggiBusStations);

        return busStationResponses;
    }

}
