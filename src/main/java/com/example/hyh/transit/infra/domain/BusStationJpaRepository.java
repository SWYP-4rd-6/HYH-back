package com.example.hyh.transit.infra.domain;

import com.example.hyh.transit.domain.BusStation;
import com.example.hyh.transit.domain.BusStationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusStationJpaRepository extends JpaRepository<BusStation, Long>, BusStationRepository {

    @Query("SELECT b FROM BusStation b WHERE b.stationName LIKE %:keyword% ORDER BY b.stationName LIMIT :limit")
    List<BusStation> searchByStationName(@Param("keyword") String keyword, @Param("limit") int limit);

    @Query(value =
            "SELECT id, ars_id, latitude, longitude, node_id, station_name, station_type, " +
                    "ST_Distance(" +
                    "ST_SetSRID(ST_MakePoint(longitude, latitude), 4326)::geography, " +
                    "ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography" +
                    ") AS distance_meters " +
                    "FROM bus_station " +
                    "ORDER BY " +
                    "ST_Distance(" +
                    "ST_SetSRID(ST_MakePoint(longitude, latitude), 4326)::geography, " +
                    "ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography" +
                    ") " +
                    "LIMIT :limit",
            nativeQuery = true)
    List<BusStation> searchNearestBusStations(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("limit") int limit);

}
