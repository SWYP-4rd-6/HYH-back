package com.example.hyh.transit.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SubwayTimeTableResponse(

        @JsonProperty("LINE_NUM")
        String lineName, // 호선
        @JsonProperty("STATION_NM")
        String stationName, // 전철역명
        @JsonProperty("TRAIN_NO")
        String trainNo, // 열차번호
        @JsonProperty("ARRIVETIME")
        String arrivalTime, // 도착시간
        @JsonProperty("LEFTTIME")
        String leftTime, // 출발시간
        @JsonProperty("SUBWAYENAME")
        String subwayEName, // 도착지하철역명
        @JsonProperty("INOUT_TAG")
        String inOutTag, // 상행,내선(1)/하행,외선(2)
        @JsonProperty("EXPRESS_YN")
        String express // 급행선(G:일반(general) D: 급행(direct))
) {
}
