package com.example.hyh.transit.domain.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GyeonggiBusStation {
    String mobileNo; // 정류소번호
    String stationId; // 정류소아이디
    String stationName; // 정류소명
    double x; // 정류소 X좌표
    double y; // 정류소 Y좌표
}
