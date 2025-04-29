package com.example.hyh.transit.domain.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RealTimeGyeonggiBusListAtStation(
        int predictTimeSec1, // 첫번째차량 도착예상시간 (몇 초후 도착예정. 초단위)
        int predictTimeSec2, // 두번째차량 도착예상시간 (몇 초후 도착예정. 초단위)
        int lowPlate1, // 첫번째차량 특수차량여부 (0: 일반버스, 1: 저상버스, 2: 2층버스, 5: 전세버스, 6: 예약버스, 7: 트롤리)
        int lowPlate2, // 두번째차량 특수차량여부 (0: 일반버스, 1: 저상버스, 2: 2층버스, 5: 전세버스, 6: 예약버스, 7: 트롤리)
        String plateNo1, // 첫번째차량 차량번호
        String plateNo2, // 두번째차량 차량번호
        String routeId, // 노선 ID
        String routeName, // 노선명
        int routeTypeCd,
        // 노선유형코드 (11: 직행좌석형시내버스, 12:좌석형시내버스, 13:일반형시내버스, 14: 광역급행형시내버스, 15: 따복형시내버스, 16: 경기순환버스, 21: 직행좌석형농어촌버스, 22: 좌석형농어촌버스, 23:일반형농어촌버스, 30: 마을버스, 41: 고속형시외버스, 42: 좌석형시외버스, 43: 일반형시외버스, 51: 리무진공항버스, 52: 좌석형공항버스, 53: 일반형공항버스)
        int stationId // 정류소아이디 (요청변수 정류소 아이디)
) {
}
