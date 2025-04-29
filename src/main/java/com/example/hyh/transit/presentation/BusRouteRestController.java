package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.BusRouteQueryService;
import com.example.hyh.transit.application.dto.BusRouteResponse;
import com.example.hyh.transit.application.dto.CityType;
import com.example.hyh.transit.application.dto.RealTimeBusByRouteAllListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/busRoutes")
@RequiredArgsConstructor
public class BusRouteRestController {

    private final BusRouteQueryService busRouteQueryService;

    @GetMapping("/search/routeName")
    public List<BusRouteResponse> searchByRouteName(@RequestParam String routeName,
                                                    @RequestParam(defaultValue = "10") int limit) {
        return busRouteQueryService.searchByRouteName(routeName, limit);
    }

    @GetMapping(value = "/search/{routeId}/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RealTimeBusByRouteAllListResponse> searchSeoulBusRealTimeById(@PathVariable String routeId,
                                                                              @RequestParam int code) {
        return code == CityType.SEOUL.getCode() ? busRouteQueryService.searchRealSeoulBusByRouteAllList(routeId).stream().map(RealTimeBusByRouteAllListResponse::of).toList()
                : null;
    }
}
