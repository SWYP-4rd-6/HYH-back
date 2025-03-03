package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.BusRouteQueryService;
import com.example.hyh.transit.application.dto.BusRouteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/busRoutes")
@RequiredArgsConstructor
public class BusRouteRestController {

    private final BusRouteQueryService busRouteQueryService;

    @GetMapping("/search/routeName")
    public List<BusRouteResponse> searchByRouteName(@RequestParam String routeName) {
        return busRouteQueryService.searchByRouteName(routeName);
    }

}
