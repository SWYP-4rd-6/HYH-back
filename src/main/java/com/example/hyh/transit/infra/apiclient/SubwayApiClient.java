package com.example.hyh.transit.infra.apiclient;

import com.example.hyh.transit.infra.apiclient.dto.SubwayRealTime;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange
public interface SubwayApiClient {

    @GetExchange("/json/realtimeStationArrival/{stIndex}/{endIndex}/{statnNm}")
    SubwayRealTime getRealTimeSubway(@PathVariable Integer stIndex,
                                     @PathVariable Integer endIndex,
                                     @PathVariable String statnNm);
}
