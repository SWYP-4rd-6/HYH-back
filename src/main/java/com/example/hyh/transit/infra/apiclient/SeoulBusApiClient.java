package com.example.hyh.transit.infra.apiclient;

import com.example.hyh.transit.infra.apiclient.dto.RealTimeSeoulBusListAtStationService;
import com.example.hyh.transit.infra.apiclient.dto.RealTimeSeoulBusService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange
public interface SeoulBusApiClient {
    @GetExchange("/getArrInfoByRouteAll")
    RealTimeSeoulBusService getSeoulBusRealTimeAll(@RequestParam String serviceKey,
                                                   @RequestParam String busRouteId);

    @GetExchange("/getLowArrInfoByStId")
    RealTimeSeoulBusListAtStationService getRealTimeSeoulBusListByStationId(@RequestParam String serviceKey,
                                                                            @RequestParam int stId);
    
}
