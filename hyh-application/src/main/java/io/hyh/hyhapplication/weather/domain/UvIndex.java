package io.hyh.hyhapplication.weather.domain;

import io.hyh.hyhapplication.common.Utils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum UvIndex {
    Low("좋음", 0, 2),
    Moderate("보통", 3, 5),
    High("높음", 6, 7),
    VeryHigh("매우 높음", 8, Integer.MAX_VALUE),
    Invalid("-", Integer.MIN_VALUE, Integer.MIN_VALUE);

    @Getter
    private final String displayName;
    private final int minValue;
    private final int maxValue;

    UvIndex(String displayName, int minValue, int maxValue) {
        this.displayName = displayName;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static UvIndex fromValue(int value) {
        for (UvIndex index : values()) {
            if (value >= index.minValue && value <= index.maxValue) {
                return index;
            }
        }
        log.info("유효하지 않은 자외선 지수: {}", value);
        return Invalid;
    }

    public static UvIndex fromValue(String value) {
        if (!Utils.isNumeric(value)) {
            log.info("수치 형식 오류: {}", value);
            return Invalid;
        }
        return fromValue(Integer.parseInt(value));
    }

}
