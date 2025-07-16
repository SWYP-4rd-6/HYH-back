package io.hyh.hyhapplication.weather.domain;

public record CurrentNowCast(
        String temperature,       // 기온
        String rainfall,          // 1시간 강수량
        String windComponentEW,   // 동서바람성분
        String windComponentNS,   // 남북바람성분
        String humidity,          // 습도
        String precipitationType, // 강수형태
        String windDirection,     // 풍향
        String windSpeed          // 풍속
) {
}
