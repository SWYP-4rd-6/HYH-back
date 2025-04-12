package com.example.hyh.transit.domain.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "itemList")
public class RealTimeSeoulBusListAtStation {
    String arrmsg1; // 첫번째 도착예정 버스의 도착정보메시지
    String arrmsg2; // 두번째 도착예정 버스의 도착정보메시지
    String busRouteAbrv; // 노선명 (안내용 – 마을버스 제외)
    String busRouteId; // 노선ID
    int busType1; // 첫번째도착예정버스의 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
    int busType2; // 두번째도착예정버스의 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
    int exps1; // 첫번째 도착예정 버스의 지수평활 도착예정시간(초)
    int exps2; // 두번째 도착예정 버스의 지수평활 도착예정시간(초)
    String mkTm; // 제공시각
    String nstnId1; // 첫번째 도착예정 버스의 다음정류소 ID
    String nstnId2; // 두번째 도착예정 버스의 다음정류소 ID
    String plainNo1; // 첫번째도착예정차량번호
    String plainNo2; // 두번째도착예정차량번호
    String stNm; // 정류소명
    int stId; // 정류소 고유 ID
}
