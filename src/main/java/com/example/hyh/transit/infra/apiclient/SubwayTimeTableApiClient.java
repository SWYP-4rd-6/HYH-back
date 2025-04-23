package com.example.hyh.transit.infra.apiclient;

import com.example.hyh.transit.infra.apiclient.dto.SubwayTimeTableService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@Component
@HttpExchange
public interface SubwayTimeTableApiClient {

    @GetExchange("/json/SearchSTNTimeTableByIDService/1/200/{stCd}/{weekTag}/{inOutTag}/")
    SubwayTimeTableService getSubwayTimeTable(@PathVariable String stCd,
                                              @PathVariable String weekTag,
                                              @PathVariable String inOutTag);
}
