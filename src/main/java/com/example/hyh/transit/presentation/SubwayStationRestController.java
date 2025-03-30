package com.example.hyh.transit.presentation;

import com.example.hyh.transit.application.SubwayStationQueryService;
import com.example.hyh.transit.application.dto.SubwayRealTimeListResponse;
import com.example.hyh.transit.application.dto.SubwayStationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subway/search")
@RequiredArgsConstructor
public class SubwayStationRestController {

    private final SubwayStationQueryService subwayStationQueryService;

    @GetMapping("/station/name")
    List<SubwayStationResponse> searchBySubwayStationName(@RequestParam String stationName,
                                                          @RequestParam(defaultValue = "10") int limit) {
        return subwayStationQueryService.searchBySubwayStationName(stationName, limit);
    }

    @GetMapping("/station/line")
    List<SubwayStationResponse> searchBySubwayStationLine(@RequestParam String stationLine,
                                                          @RequestParam(defaultValue = "10") int limit) {
        return subwayStationQueryService.searchBySubwayStationLine(stationLine, limit);
    }

    @GetMapping("/{statnName}/list")
    List<SubwayRealTimeListResponse> getRealTimeSubwayList(@PathVariable String statnName){
        return subwayStationQueryService.getRealTimeSubwayList(statnName);
    }
}
