package io.hyh.hyhapplication.weather.domain;

import io.hyh.hyhapplication.common.Utils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum RainfallIntensity {
    NoRain("비 없음", 0, 0),
    Moderate("보통 비", 0, 0.9),
    Heavy("강한 비", 1, 2.9),
    VeryHeavy("매우 강한 비", 3, Double.MAX_VALUE),
    Invalid("-", Double.MIN_VALUE, Double.MIN_VALUE);

    @Getter
    private final String displayName;
    private final double minValue;
    private final double maxValue;

    RainfallIntensity(String displayName, double minValue, double maxValue) {
        this.displayName = displayName;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static RainfallIntensity fromValue(double value) {
        for (RainfallIntensity intensity : values()) {
            if (value >= intensity.minValue && value <= intensity.maxValue) {
                return intensity;
            }
        }
        log.info("유효하지 않은 강우량: {}", value);
        return Invalid;
    }

    public static RainfallIntensity fromValue(String value) {
        if ("강수없음".equals(value)) {
            return NoRain;
        }

        if (!Utils.isNumeric(value)) {
            log.info("수치 형식 오류: {}", value);
            return Invalid;
        }

        return fromValue(Double.parseDouble(value));
    }

}
