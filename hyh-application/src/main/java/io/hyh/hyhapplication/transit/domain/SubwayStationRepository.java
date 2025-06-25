package io.hyh.hyhapplication.transit.domain;

import java.util.List;

public interface SubwayStationRepository {

    List<SubwayStation> searchBySubwayStationName(String station, int limit);

    List<SubwayStation> searchBySubwayStationLine(String stationLine, int limit);
}
