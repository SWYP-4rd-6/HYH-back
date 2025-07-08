package io.hyh.hyhapplication.weather.infra.persistence.repository;

import io.hyh.hyhapplication.weather.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherDataJpaRepository extends JpaRepository<WeatherData, Long> {

    @Query("""
            SELECT w FROM WeatherData w
            WHERE w.fcstDate = :fcstDate
            AND w.fcstTime IN :fcstTimes
            AND w.category IN :categories
            AND w.nx = :nx AND w.ny = :ny
            """)
    List<WeatherData> findSelectedFields(
            @Param("fcstDate") String fcstDate,
            @Param("fcstTimes") List<String> fcstTimes,
            @Param("categories") List<String> categories,
            @Param("nx") int nx,
            @Param("ny") int ny);

    @Query("""
            SELECT w FROM WeatherData w
            WHERE w.fcstDate = :fcstDate
            AND w.category IN :categories
            AND w.nx = :nx AND w.ny = :ny
            """)
    List<WeatherData> findSelectedFields(
            @Param("fcstDate") String fcstDate,
            @Param("categories") List<String> categories,
            @Param("nx") int nx,
            @Param("ny") int ny);
}
