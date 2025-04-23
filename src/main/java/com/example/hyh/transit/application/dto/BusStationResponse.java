package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.BusStation;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record BusStationResponse(
        String nodeId,
        String stId,
        String stationName,
        BigDecimal latitude,
        BigDecimal longitude
) {
    public static BusStationResponse of(BusStation busStation) {
        return new BusStationResponse(
                busStation.getStationId(),
                busStation.getOriginStationId(),
                busStation.getStationName(),
                busStation.getLatitude(),
                busStation.getLongitude()
        );
    }

    public static List<BusStationResponse> listOf(List<BusStation> busStations) {
        List<BusStationResponse> busStationResponses = busStations.stream()
                .map(BusStationResponse::of)
                .collect(Collectors.toList());

        return busStationResponses;
    }

}
