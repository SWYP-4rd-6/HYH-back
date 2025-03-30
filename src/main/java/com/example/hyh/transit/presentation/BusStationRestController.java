package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.BusStationQueryService;
import com.example.hyh.transit.application.dto.BusStationResponse;
import com.example.hyh.transit.application.dto.GyeonggiBusRealTimeListResponse;
import com.example.hyh.transit.application.dto.GyeonggiBusRealTimeResponse;
import com.example.hyh.transit.application.dto.GyeonggiBusStationIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/search/gyeongiBus")
    List<GyeonggiBusStationIdResponse> searchByGyeonggiBusStationName(@RequestParam String stationName) throws IOException {
        return busStationQueryService.getGyeongiBusStationId(stationName);
    }

    @GetMapping("/search/{stationId}/list")
    List<GyeonggiBusRealTimeListResponse> searchGyeonggiBusRealTimeById(@PathVariable int stationId) throws IOException {
        return busStationQueryService.getGyeonggiBusRealTimeById(stationId);
    }
}
