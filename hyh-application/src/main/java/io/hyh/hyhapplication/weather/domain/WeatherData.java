package io.hyh.hyhapplication.weather.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "weather_data")
@NoArgsConstructor
@Getter
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_date", nullable = false)
    private String baseDate;

    @Column(name = "base_time", nullable = false)
    private String baseTime;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "fcst_date", nullable = false)
    private String fcstDate;

    @Column(name = "fcst_time", nullable = false)
    private String fcstTime;

    @Column(name = "fcst_value", nullable = false)
    private String fcstValue;

    @Column(name = "nx", nullable = false)
    private int nx;

    @Column(name = "ny", nullable = false)
    private int ny;

    @Column(name = "data_type", nullable = false)
    private String dataType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Builder
    public WeatherData(String baseDate,
                       String baseTime,
                       String category,
                       String fcstDate,
                       String fcstTime,
                       String fcstValue,
                       int nx,
                       int ny,
                       String dataType) {
        this.baseDate = baseDate;
        this.baseTime = baseTime;
        this.category = category;
        this.fcstDate = fcstDate;
        this.fcstTime = fcstTime;
        this.fcstValue = fcstValue;
        this.nx = nx;
        this.ny = ny;
        this.dataType = dataType;
        this.createdAt = ZonedDateTime.now();
    }

}
