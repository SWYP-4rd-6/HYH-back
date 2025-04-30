package com.example.hyh.weather.domain;

import com.example.hyh.global.Utils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Slf4j
public class AirQuality {

    public enum Pm10 {
        Good("좋음", 0, 30),
        Moderate("보통", 31, 80),
        Bad("나쁨", 81, 150),
        VeryBad("매우 나쁨", 151, Integer.MAX_VALUE),
        Invalid("-", Integer.MIN_VALUE, Integer.MIN_VALUE);

        @Getter
        private final String displayName;
        private final int minValue;
        private final int maxValue;

        Pm10(String displayName, int minValue, int maxValue) {
            this.displayName = displayName;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public static @NotNull Pm10 fromValue(int value) {
            for (Pm10 quality : values()) {
                if (value >= quality.minValue && value <= quality.maxValue) {
                    return quality;
                }
            }
            log.info("유효하지 않은 미세먼지(pm10) 수치: {}", value);
            return Invalid;
        }

        public static @NotNull Pm10 fromValue(@Nullable String stringValue) {
            if (!Utils.isNumeric(stringValue)) {
                log.info("수치 형식 오류: {}", stringValue);
                return Invalid;
            }
            return fromValue(Integer.parseInt(stringValue));
        }
    }

    public enum Pm25 {
        Good("좋음", 0, 15),
        Moderate("보통", 16, 35),
        Bad("나쁨", 36, 75),
        VeryBad("매우 나쁨", 75, Integer.MAX_VALUE),
        Invalid("-", Integer.MIN_VALUE, Integer.MIN_VALUE);

        @Getter
        private final String displayName;
        private final int minValue;
        private final int maxValue;

        Pm25(String displayName, int minValue, int maxValue) {
            this.displayName = displayName;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public static @NotNull Pm25 fromValue(int value) {
            for (Pm25 quality : values()) {
                if (value >= quality.minValue && value <= quality.maxValue) {
                    return quality;
                }
            }
            log.info("유효하지 않은 미세먼지(pm2.5) 수치: {}", value);
            return Invalid;
        }

        public static @NotNull Pm25 fromValue(@Nullable String stringValue) {
            if (!Utils.isNumeric(stringValue)) {
                return Invalid;
            }
            assert stringValue != null;
            return fromValue(Integer.parseInt(stringValue));
        }
    }

}
