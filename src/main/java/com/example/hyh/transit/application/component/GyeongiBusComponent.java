package com.example.hyh.transit.application.component;

import com.example.hyh.transit.application.dto.GyeonggiBustStationResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@Component
@HttpExchange
public interface GyeongiBusComponent {
    @GetExchange("/busstationservice/v2/getBusStationListv2")
    String getGyeongiBusStationId(@RequestParam String serviceKey,
                                  @RequestParam String keyword,
                                  @RequestParam String format);
}
