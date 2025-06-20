package io.hyh.hyhapplication.transit.application.dto;

import io.hyh.hyhapplication.transit.domain.BusRoute;
import io.hyh.hyhapplication.transit.domain.BusStation;
import io.hyh.hyhapplication.transit.domain.SubwayStation;

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
