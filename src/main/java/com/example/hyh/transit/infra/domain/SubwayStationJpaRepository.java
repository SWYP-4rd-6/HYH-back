package com.example.hyh.transit.infra.domain;

import com.example.hyh.transit.domain.SubwayStation;
import com.example.hyh.transit.domain.SubwayStationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubwayStationJpaRepository extends JpaRepository<SubwayStation, Long>, SubwayStationRepository {

    @Query("SELECT s FROM SubwayStation s WHERE s.subwayStationName LIKE %:keyword% ORDER BY s.subwayStationName LIMIT :limit")
    List<SubwayStation> searchBySubwayStationName(@Param("keyword") String keyword, @Param("limit") int limit);

    @Query("SELECT s FROM SubwayStation s WHERE s.subwayStationLine = :keyword ORDER BY s.subwayStationName LIMIT :limit")
    List<SubwayStation> searchBySubwayStationLine(@Param("keyword") String keyword, @Param("limit") int limit);
}
