package io.hyh.hyhapi.transit;

import io.hyh.hyhapi.common.dto.Response;
import io.hyh.hyhapplication.transit.application.SubwayStationQueryService;
import io.hyh.hyhapplication.transit.application.dto.SubwayRealTimeListResponse;
import io.hyh.hyhapplication.transit.application.dto.SubwayStationResponse;
import io.hyh.hyhapplication.transit.application.dto.SubwayTimeTableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subway/search")
@RequiredArgsConstructor
public class SubwayStationRestController {

    private final SubwayStationQueryService subwayStationQueryService;

    @GetMapping("/station/name")
    public Response<List<SubwayStationResponse>> searchBySubwayStationName(@RequestParam String stationName,
                                                                           @RequestParam(defaultValue = "10") int limit) {
        return Response.success(subwayStationQueryService.searchBySubwayStationName(stationName, limit));
    }

    @GetMapping("/station/line")
    public Response<List<SubwayStationResponse>> searchBySubwayStationLine(@RequestParam String stationLine,
                                                                           @RequestParam(defaultValue = "10") int limit) {
        return Response.success(subwayStationQueryService.searchBySubwayStationLine(stationLine, limit));
    }

    @GetMapping("/{statnName}/list")
    public Response<List<SubwayRealTimeListResponse>> searchRealTimeSubwayList(@PathVariable String statnName) {
        return Response.success(subwayStationQueryService.searchRealTimeSubwayList(statnName));
    }

    @GetMapping("/{stCd}/{weekTag}/{inOutTag}")
    public Response<List<SubwayTimeTableResponse>> searchSubwayTimeTableList(@PathVariable String stCd,
                                                                             @PathVariable String weekTag,
                                                                             @PathVariable String inOutTag) {
        return Response.success(subwayStationQueryService.searchSubwayTimeTableList(stCd, weekTag, inOutTag));
    }

}
