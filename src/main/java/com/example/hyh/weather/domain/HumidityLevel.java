package com.example.hyh.weather.domain;

import com.example.hyh.global.Utils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum HumidityLevel {
    Dry("건조", 0, 30),
    Comfortable("쾌적", 31, 60),
    SomewhatHumid("다소 습함", 61, 80),
    VeryHumid("매우 습함", 81, 100),
    Invalid("-", Integer.MIN_VALUE, Integer.MIN_VALUE);

    @Getter
    private final String displayName;
    private final int minValue;
    private final int maxValue;

    HumidityLevel(String displayName, int minValue, int maxValue) {
        this.displayName = displayName;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static HumidityLevel fromValue(int value) {
        for (HumidityLevel level : values()) {
            if (value >= level.minValue && value <= level.maxValue) {
                return level;
            }
        }
        log.info("유효하지 않은 습도 수치: {}", value);
        return Invalid;
    }

    public static HumidityLevel fromValue(String value) {
        if (!Utils.isNumeric(value)) {
            log.info("수치 형식 오류: {}", value);
            return Invalid;
        }
        return fromValue(Integer.parseInt(value));
    }

}
