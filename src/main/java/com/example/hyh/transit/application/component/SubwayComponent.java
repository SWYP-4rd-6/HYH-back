package com.example.hyh.transit.application.component;

import com.example.hyh.transit.application.dto.SubwayRealTimeResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange
public interface SubwayComponent {

    @GetExchange("/json/realtimeStationArrival/{stIndex}/{endIndex}/{statnNm}")
    SubwayRealTimeResponse getRealTimeSubway(@PathVariable Integer stIndex,
                                             @PathVariable Integer endIndex,
                                             @PathVariable String statnNm);
}
