package com.example.hyh.transit.infra.component;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange
public interface SeoulBusComponent {
    @GetExchange("/getArrInfoByRouteAll")
    String getSeoulBusRealTimeAll(@RequestParam String serviceKey,
                                  @RequestParam String busRouteId);

    @GetExchange("/getLowArrInfoByStId")
    String getRealTimeSeoulBusListByStationId(@RequestParam String serviceKey,
                                              @RequestParam int stId);

    @GetExchange("/getArrInfoByRoute")
    String getSeoulBusRealTimeStation(@RequestParam String serviceKey,
                                      @RequestParam String stationId,
                                      @RequestParam String busRouteId,
                                      @RequestParam int ord);
}
