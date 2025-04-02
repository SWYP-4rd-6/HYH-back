package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.BusRoute;
import com.example.hyh.transit.domain.BusStation;
import com.example.hyh.transit.domain.SubwayStation;

import java.util.List;

public record TransitSearchResult(
        List<BusRouteResponse> busRoutes,
        List<BusStationResponse> busStations,
        List<SubwayStationResponse> subwayStations
) {

    public static TransitSearchResult of(List<BusRoute> busRoutes, List<BusStation> busStations, List<SubwayStation> subwayStations) {
        return new TransitSearchResult(
                BusRouteResponse.listOf(busRoutes),
                BusStationResponse.listOf(busStations),
                SubwayStationResponse.listOf(subwayStations)
        );
    }
}
