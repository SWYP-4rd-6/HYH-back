package com.example.hyh.transit.application.component;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@Component
@HttpExchange
public interface GyeongiBusComponent {
    @GetExchange("/busstationservice/v2/getBusStationListv2")
    String getGyeongiBusStationId(@RequestParam String serviceKey,
                                  @RequestParam String keyword,
                                  @RequestParam String format);

    @GetExchange("/busarrivalservice/v2/getBusArrivalListv2")
    String getGyeonggiBusRealtime(@RequestParam String serviceKey,
                                  @RequestParam int stationId,
                                  @RequestParam String format);
}
