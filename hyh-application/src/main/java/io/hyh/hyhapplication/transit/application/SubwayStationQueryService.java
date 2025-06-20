package io.hyh.hyhapplication.transit.application;

import io.hyh.hyhapplication.transit.application.dto.SubwayRealTimeListResponse;
import io.hyh.hyhapplication.transit.application.dto.SubwayStationResponse;
import io.hyh.hyhapplication.transit.application.dto.SubwayTimeTableResponse;
import io.hyh.hyhapplication.transit.domain.SubwayStationRepository;
import io.hyh.hyhapplication.transit.infra.apiclient.SubwayApiClient;
import io.hyh.hyhapplication.transit.infra.apiclient.SubwayTimeTableApiClient;
import io.hyh.hyhapplication.transit.infra.apiclient.dto.SubwayRealTime;
import io.hyh.hyhapplication.transit.infra.apiclient.dto.SubwayTimeTableService;
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
