package com.example.hyh.transit.infra.apiclient;

import com.example.hyh.transit.infra.apiclient.dto.RealTimeGyeonggiBusListAtStationService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;


@Component
@HttpExchange
public interface GyeonggiBusApiClient {
    @GetExchange("/busarrivalservice/v2/getBusArrivalListv2")
    RealTimeGyeonggiBusListAtStationService getRealTimeGyeonggiBusListByStationId(@RequestParam String serviceKey,
                                                                                  @RequestParam int stationId,
                                                                                  @RequestParam String format);
}
