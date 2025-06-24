package io.hyh.hyhapplication.weather.infra.apiclient.air;

public record WeeklyDustForecastItem(
        String frcstOneDt,    // 예보 1일차 날짜(yyyy-MM-dd)
        String frcstOneCn,    // 예보 1일차 내용
        String frcstTwoDt,    // 예보 2일차 날짜  (yyyy-MM-dd)
        String frcstTwoCn,    // 예보 2일차 내용
        String frcstThreeDt,  // 예보 3일차 날짜(yyyy-MM-dd)
        String frcstThreeCn,  // 예보 3일차 내용
        String frcstFourDt,   // 예보 4일차 날짜(yyyy-MM-dd)
        String frcstFourCn,   // 예보 4일차 내용
        String gwthcnd,       // 대기질 예보 근거
        String presnatnDt     // 발표일시(yyyy-MM-dd)
) {
}
