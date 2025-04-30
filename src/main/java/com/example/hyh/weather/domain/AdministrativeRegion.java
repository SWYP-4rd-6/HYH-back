package com.example.hyh.weather.domain;

import java.math.BigDecimal;

public record AdministrativeRegion(
        String category,
        String adminCode,
        String regionDepth1,
        String regionDepth2,
        String regionDepth3,
        int gridX,
        int gridY,
        int longitudeDegree,
        int longitudeMinute,
        BigDecimal longitudeSecond,
        int latitudeDegree,
        int latitudeMinute,
        BigDecimal latitudeSecond,
        BigDecimal longitudeDecimal,
        BigDecimal latitudeDecimal
) {
}
