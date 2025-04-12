package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.BusRouteQueryService;
import com.example.hyh.transit.application.dto.BusRouteResponse;
import com.example.hyh.transit.application.dto.CityType;
import com.example.hyh.transit.application.dto.RealTimeBusByRouteAllList;
import com.example.hyh.transit.domain.openApi.RealTimeGyeonggiBusListAtStation;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusListAtStation;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/search/{routeId}/list")
    public List<RealTimeBusByRouteAllList> searchSeoulBusRealTimeById(@PathVariable String routeId,
                                                                      @RequestParam CityType type) throws JsonProcessingException {
        return type.equals(CityType.SEOUL) ? busRouteQueryService.searchRealSeoulBusByRouteAllList(routeId).stream().map(RealTimeBusByRouteAllList::of).toList()
                : null;
    }

    @GetMapping("/search/{stationId}/{routeId}/{ord}/list")
    public List<RealTimeSeoulBusListAtStation> searchSeoulBusRealTimeStation(@PathVariable String stationId, @PathVariable String routeId, @PathVariable int ord) throws JsonProcessingException {
        return busRouteQueryService.searchSeoulBusRealTimeStation(stationId, routeId, ord);
    }

    @GetMapping("/search/g/{stationId}/{routeId}/{ord}/list")
    public List<RealTimeGyeonggiBusListAtStation> searchSeoulBusRealTimeStation(@PathVariable int stationId, @PathVariable String routeId, @PathVariable int ord) throws IOException {
        return busRouteQueryService.getGyeonggiBusRealTimeStation(stationId, routeId, ord, "json");
    }
}
