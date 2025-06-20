package io.hyh.hyhapplication.transit.domain.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RealTimeSeoulBusByRouteAllList(
        String arrmsg1, // 첫번째 도착예정 버스의 도착정보메시지
        String arrmsg2, // 두번째 도착예정 버스의 도착정보메시지
        String busRouteAbrv, // 노선명 (안내용 – 마을버스 제외)
        String busRouteId, // 노선ID
        int busType1, // 첫번째도착예정버스의 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
        int busType2, // 두번째도착예정버스의 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
        int exps1, // 첫번째 도착예정 버스의 지수평활 도착예정시간(초)
        int exps2, // 두번째 도착예정 버스의 지수평활 도착예정시간(초)
        String firstTm, // 첫차시간
        int isLast1, // 첫번째도착예정버스의 막차여부 (0:막차아님, 1:막차)
        int isLast2, // 두번째도착예정버스의 막차여부 (0:막차아님, 1:막차)
        String lastTm, // 막차시간
        String mkTm, // 제공시각
        String plainNo1, // 첫번째도착예정차량번호
        String plainNo2, // 두번째도착예정차량번호
        int stId, // 정류소 고유 ID
        String stNm, // 정류소명
        int term // 배차간격 (분)
) {
}
