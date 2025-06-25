package io.hyh.hyhapplication.transit.application.dto;

public record SubwayRealTimeListResponse(

        String subwayId, // 지하철호선ID
        String updnLine, // 상하행선구분
        String trainLineNm, // 도착지방면(목적지-다음역)
        String statnFid, // 이전지하철ID
        String statnTid, // 다음지하철ID
        String statnId, // 지하철역ID
        String statnNm, // 지하철역명
        String ordkey, // 도착예정열차순번 (상하행코드(1자리), 순번(첫번째, 두번째 열차 , 1자리), 첫번째 도착예정 정류장 - 현재 정류장(3자리), 목적지 정류장, 급행여부(1자리))
        String subwayList, // 연계호선ID(1002, 1007 등 연계대상 호선ID)
        String statnList, // 연계지하철역ID(1002000233, 1007000000)
        String btrainSttus, // 열차종류(급행,ITX,일반,특급)
        String barvlDt, // 열차도착예정시간(단위:초)
        String btrainNo, // 열차번호(현재운행하고 있는 호선별 열차번호)
        String bstatnId, // 종착지하철역ID
        String bstatnNm, // 종착지하철역명
        String arvlMsg2, // 첫번째도착메세지(도착, 출발 , 진입 등)
        String arvlMsg3, // 두번째도착메세지(종합운동장 도착, 12분 후 (광명사거리) 등)
        String arvlCd, // 도착코드(0:진입, 1:도착, 2:출발, 3:전역출발, 4:전역진입, 5:전역도착, 99:운행중)
        String lstcarAt // 막차여부(1:막차, 0:아님)

) { }
