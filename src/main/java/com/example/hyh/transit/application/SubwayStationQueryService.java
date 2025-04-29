package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.SubwayRealTimeListResponse;
import com.example.hyh.transit.application.dto.SubwayStationResponse;
import com.example.hyh.transit.application.dto.SubwayTimeTableResponse;
import com.example.hyh.transit.domain.SubwayStationRepository;
import com.example.hyh.transit.infra.apiclient.SubwayApiClient;
import com.example.hyh.transit.infra.apiclient.SubwayTimeTableApiClient;
import com.example.hyh.transit.infra.apiclient.dto.SubwayRealTime;
import com.example.hyh.transit.infra.apiclient.dto.SubwayTimeTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubwayStationQueryService {

    private final SubwayStationRepository subwayStationRepository;
    private final SubwayApiClient subwayApiClient;
    private final SubwayTimeTableApiClient subwayTimeTableApiClient;

    public List<SubwayStationResponse> searchBySubwayStationName(String stationName, int limit) {
        return subwayStationRepository.searchBySubwayStationName(stationName, limit)
                .stream()
                .map(SubwayStationResponse::of)
                .toList();
    }

    public List<SubwayStationResponse> searchBySubwayStationLine(String stationLine, int limit) {
        return subwayStationRepository.searchBySubwayStationLine(stationLine, limit)
                .stream()
                .map(SubwayStationResponse::of)
                .toList();
    }

    public List<SubwayRealTimeListResponse> searchRealTimeSubwayList(String statnNm) {
        SubwayRealTime subwayRealTime = subwayApiClient.getRealTimeSubway(0, 20, statnNm);

        return subwayRealTime.arrivalList();
    }

    public List<SubwayTimeTableResponse> searchSubwayTimeTableList(String stCd,
                                                                   String weekTag,
                                                                   String inOutTag) {
        SubwayTimeTableService service = subwayTimeTableApiClient.getSubwayTimeTable(stCd, weekTag, inOutTag);

        return service.service().subwayTimetableList();
    }
}
