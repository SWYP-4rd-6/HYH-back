package io.hyh.hyhapplication.transit.application.dto;

import io.hyh.hyhapplication.transit.domain.BusRoute;

import java.util.List;

public record BusRouteResponse(
        String routeId,
        String routeName,
        int ord,
        String nodeId,
        String stationName
) {
    public static BusRouteResponse of(BusRoute busRoute) {
        return new BusRouteResponse(busRoute.getRouteId(), busRoute.getRouteName(), busRoute.getOrd(), busRoute.getNodeId(), busRoute.getStationName());
    }

    public static List<BusRouteResponse> listOf(List<BusRoute> busRoutes) {
        return busRoutes.stream()
                .map(BusRouteResponse::of)
                .toList();
    }

}
