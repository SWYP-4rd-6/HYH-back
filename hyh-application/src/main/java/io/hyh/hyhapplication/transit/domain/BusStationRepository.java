package io.hyh.hyhapplication.transit.domain;

import java.util.List;

public interface BusStationRepository {

    List<BusStation> searchByStationName(String stationName, int limit);

    List<BusStation> searchNearestBusStations(double latitude, double longitude, int limit);

}
