package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.BusStationQueryService;
import com.example.hyh.transit.application.dto.BusStationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/busStations")
@RequiredArgsConstructor
public class BusStationRestController {

    private final BusStationQueryService busStationQueryService;

    @GetMapping("/search/stationName")
    List<BusStationResponse> searchByStationName(@RequestParam String stationName,
                                                 @RequestParam(defaultValue = "10") int limit) {
        return busStationQueryService.searchByStationName(stationName, limit);
    }

    @GetMapping("/search/nearest")
    List<BusStationResponse> searchNearest(@RequestParam double latitude,
                                           @RequestParam double longitude,
                                           @RequestParam(defaultValue = "10") int limit) {
        return busStationQueryService.searchNearestBusStations(latitude, longitude, limit);
    }

}
