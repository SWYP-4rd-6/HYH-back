package io.hyh.hyhapi.transit;

import io.hyh.hyhapi.common.dto.Response;
import io.hyh.hyhapplication.transit.application.BusRouteQueryService;
import io.hyh.hyhapplication.transit.application.dto.BusRouteResponse;
import io.hyh.hyhapplication.transit.application.dto.CityType;
import io.hyh.hyhapplication.transit.application.dto.RealTimeBusByRouteAllListResponse;
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
    public Response<List<BusRouteResponse>> searchByRouteName(@RequestParam String routeName,
                                                              @RequestParam(defaultValue = "10") int limit) {

        return Response.success(busRouteQueryService.searchByRouteName(routeName, limit));
    }

    @GetMapping(value = "/search/{routeId}/list",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<RealTimeBusByRouteAllListResponse>> searchSeoulBusRealTimeById(@PathVariable String routeId,
                                                                                        @RequestParam int code) {
        return code == CityType.SEOUL.getCode() ?
                Response.success(busRouteQueryService.searchRealSeoulBusByRouteAllList(routeId).stream().map(RealTimeBusByRouteAllListResponse::of).toList())
                : Response.success(null);
    }
}
