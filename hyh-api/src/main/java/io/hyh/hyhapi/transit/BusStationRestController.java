package io.hyh.hyhapi.transit;

import io.hyh.hyhapi.common.dto.Response;
import io.hyh.hyhapplication.transit.application.BusStationQueryService;
import io.hyh.hyhapplication.transit.application.dto.BusStationResponse;
import io.hyh.hyhapplication.transit.application.dto.CityType;
import io.hyh.hyhapplication.transit.application.dto.RealTimeBusListAtStationResponse;
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
    public Response<List<BusStationResponse>> searchByStationName(@RequestParam String stationName,
                                                                  @RequestParam(defaultValue = "10") int limit) {
        return Response.success(busStationQueryService.searchByStationName(stationName, limit));
    }

    @GetMapping("/search/nearest")
    public Response<List<BusStationResponse>> searchNearest(@RequestParam double latitude,
                                                            @RequestParam double longitude,
                                                            @RequestParam(defaultValue = "10") int limit) {
        return Response.success(busStationQueryService.searchNearestBusStations(latitude, longitude, limit));
    }

    @GetMapping("/search/realTimeStation")
    public Response<List<RealTimeBusListAtStationResponse>> searchRealBusListByStationId(@RequestParam int stId,
                                                                                         @RequestParam int code) {
        return code == CityType.SEOUL.getCode() ?
                Response.success(busStationQueryService.searchRealSeoulBusListByStationId(stId).stream().map(RealTimeBusListAtStationResponse::of).toList())
                : Response.success(busStationQueryService.searchRealGyeonggiBusListByStationId(stId).stream().map(RealTimeBusListAtStationResponse::of).toList());
    }
}
