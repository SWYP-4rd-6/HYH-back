package com.example.hyh.transit.application.dto;

import com.example.hyh.transit.domain.BusRoute;

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
}
