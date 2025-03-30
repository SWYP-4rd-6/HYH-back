package com.example.hyh.transit.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GyeonggiBusRealTimeListResponse(
        Integer crowded1, // 첫번째차량 차내혼잡도 (1:여유, 2:보통, 3:혼잡, 4:매우혼잡) 차내혼잡도 제공노선유형 (13:일반형시내버스, 15:따복형시내버스, 23:일반형농어촌버스)
        Integer crowded2, // 두번째차량 차내혼잡도 (1:여유, 2:보통, 3:혼잡, 4:매우혼잡) 차내혼잡도 제공노선유형 (13:일반형시내버스, 15:따복형시내버스, 23:일반형농어촌버스)
        String flag, // 상태구분(RUN:운행중 PASS:운행중 STOP:운행종료 WAIT:회차지대기)
        Integer locationNo1, // 첫번째차량 위치 정보 (몇번째전 정류소)
        Integer locationNo2, // 두번째차량 위치 정보 (몇번째전 정류소)
        Integer lowPlate1, // 첫번째차량 특수차량여부 (0: 일반버스, 1: 저상버스, 2: 2층버스, 5: 전세버스, 6: 예약버스, 7: 트롤리)
        Integer lowPlate2, // 두번째차량 특수차량여부 (0: 일반버스, 1: 저상버스, 2: 2층버스, 5: 전세버스, 6: 예약버스, 7: 트롤리)
        String plateNo1, // 첫번째차량 차량번호
        String plateNo2, // 두번째차량 차량번호
        Integer predictTime1, // 첫번째차량 도착예상시간 (몇 분후 도착예정. 분단위)
        Integer predictTime2, // 두번째차량 도착예상시간 (몇 분후 도착예정. 분단위)
        Integer remainSeatCnt1, // 첫번째 차량 차내빈자리수 (-1:정보없음, 0~:빈자리 수) 차내빈자리수 제공노선유형 (11: 직행좌석형시내버스, 12:좌석형시내버스, 14: 광역급행형시내버스, 16: 경기순환버스, 17: 준공영제직행좌석시내버스, 21: 직행좌석형농어촌버스, 22: 좌석형농어촌버스)
        Integer remainSeatCnt2, // 두번째 차량 차내빈자리수 (-1:정보없음, 0~:빈자리 수) 차내빈자리수 제공노선유형 (11: 직행좌석형시내버스, 12:좌석형시내버스, 14: 광역급행형시내버스, 16: 경기순환버스, 17: 준공영제직행좌석시내버스, 21: 직행좌석형농어촌버스, 22: 좌석형농어촌버스)
        Integer routeDestId, // 진행방향 마지막 정류소아이디
        String routeDestName, // 진행방향 마지막 정류소명
        Integer routeId, // 노선아이디
        String routeName, // 노선명
        Integer routeTypeCd, // 노선유형코드 (11: 직행좌석형시내버스, 12:좌석형시내버스, 13:일반형시내버스, 14: 광역급행형시내버스, 15: 따복형시내버스, 16: 경기순환버스, 21: 직행좌석형농어촌버스, 22: 좌석형농어촌버스, 23:일반형농어촌버스, 30: 마을버스, 41: 고속형시외버스, 42: 좌석형시외버스, 43: 일반형시외버스, 51: 리무진공항버스, 52: 좌석형공항버스, 53: 일반형공항버스)
        Integer staOrder, // 정류소순번 (요청변수 정류소가 노선의 몇번째 정류소인지 나타냄)
        Integer stationId, // 정류소아이디 (요청변수 정류소 아이디)
        String stationNm1, // 첫번째차량 위치 정류소명
        String stationNm2, // 두번째차량 위치 정류소명
        Integer taglessCd1, // 첫번째 차량 태그리스 서비스 제공여부 (0:일반차량, 1:태그리스차량)
        Integer taglessCd2, // 두번째 차량 태그리스 서비스 제공여부 (0:일반차량, 1:태그리스차량)
        Integer turnSeq, // 노선의 회차점 순번
        Integer vehId1, // 첫번째 차량 차량아이디
        Integer vehId2, // 두번째 차량 차량아이디
        Integer predictTimeSec1, // 첫번째차량 도착예상시간 (몇 초후 도착예정. 초단위)
        Integer predictTimeSec2, // 두번째차량 도착예상시간 (몇 초후 도착예정. 초단위)
        Integer stateCd1 // 첫번째차량 상태코드 (0:교차로통과, 1:정류소 도착, 2:정류소 출발)
) {
}
