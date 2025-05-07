package com.example.hyh.weather.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "administrative_region")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdministrativeRegionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category", nullable = false, length = 10, insertable = false, updatable = false)
    private String category;

    @Column(name = "admin_code", nullable = false, length = 20, insertable = false, updatable = false)
    private String adminCode;

    @Column(name = "region_depth1", nullable = false, length = 50, insertable = false, updatable = false)
    private String regionDepth1;

    @Column(name = "region_depth2", length = 50, insertable = false, updatable = false)
    private String regionDepth2;

    @Column(name = "region_depth3", length = 50, insertable = false, updatable = false)
    private String regionDepth3;

    @Column(name = "grid_x", nullable = false, insertable = false, updatable = false)
    private int gridX;

    @Column(name = "grid_y", nullable = false, insertable = false, updatable = false)
    private int gridY;

    @Column(name = "longitude_degree", nullable = false, insertable = false, updatable = false)
    private int longitudeDegree;

    @Column(name = "longitude_minute", nullable = false, insertable = false, updatable = false)
    private int longitudeMinute;

    @Column(name = "longitude_second", nullable = false, precision = 10, scale = 2, insertable = false, updatable = false)
    private BigDecimal longitudeSecond;

    @Column(name = "latitude_degree", nullable = false, insertable = false, updatable = false)
    private int latitudeDegree;

    @Column(name = "latitude_minute", nullable = false, insertable = false, updatable = false)
    private int latitudeMinute;

    @Column(name = "latitude_second", nullable = false, precision = 10, scale = 2, insertable = false, updatable = false)
    private BigDecimal latitudeSecond;

    @Column(name = "longitude_decimal", nullable = false, precision = 15, scale = 12, insertable = false, updatable = false)
    private BigDecimal longitudeDecimal;

    @Column(name = "latitude_decimal", nullable = false, precision = 15, scale = 12, insertable = false, updatable = false)
    private BigDecimal latitudeDecimal;

}
