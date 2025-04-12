package com.example.hyh.transit.infra.component;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@Component
@HttpExchange
public interface GyeonggiBusComponent {
    @GetExchange("/busstationservice/v2/getBusStationListv2")
    String getGyeongiBusStationId(@RequestParam String serviceKey,
                                  @RequestParam String keyword,
                                  @RequestParam String format);

    @GetExchange("/busarrivalservice/v2/getBusArrivalListv2")
    String getRealTimeGyeonggiBusListByStationId(@RequestParam String serviceKey,
                                                 @RequestParam int stId,
                                                 @RequestParam String format);

    @GetExchange("/busarrivalservice/v2/getBusArrivalItemv2")
    String getGyeonggiBusRealtimeStation(@RequestParam String serviceKey,
                                         @RequestParam int stationId,
                                         @RequestParam String routeId,
                                         @RequestParam int strOrder,
                                         @RequestParam String format);
}
