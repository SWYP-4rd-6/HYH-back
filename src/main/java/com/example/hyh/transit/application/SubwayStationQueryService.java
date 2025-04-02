package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.SubwayStationResponse;
import com.example.hyh.transit.domain.SubwayStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubwayStationQueryService {

    private final SubwayStationRepository subwayStationRepository;

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
}
