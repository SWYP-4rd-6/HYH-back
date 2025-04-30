package com.example.hyh.weather.domain;

import org.springframework.util.StringUtils;

public record Location(
        String city,
        String dong
) {
    public Location {
        if (!StringUtils.hasText(city)) {
            throw new IllegalArgumentException("시 이름이 null 또는 빈 문자열입니다.");
        }
        if (!StringUtils.hasText(dong)) {
            throw new IllegalArgumentException("동 이름이 null 또는 빈 문자열입니다.");
        }
    }

    public String getFullAddress() {
        return String.format("%s %s ", city, dong);
    }

}