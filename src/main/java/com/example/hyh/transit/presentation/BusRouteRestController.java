package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.BusRouteQueryService;
import com.example.hyh.transit.application.dto.BusRouteResponse;
import com.example.hyh.transit.application.dto.SeoulBusRealTimeAllResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/search/{routeId}/list")
    public List<SeoulBusRealTimeAllResponse> searchSeoulBusRealTimeById(@PathVariable String routeId) throws JsonProcessingException {
        return busRouteQueryService.searchSeoulBusRealTimeAllById(routeId);
    }
}
