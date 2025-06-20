package io.hyh.hyhapplication.weather.infra.apiclient.response;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;


public record WeatherForecastApiResponse(
        Response<UltraSrtFcstItem> response
) {

    public List<String> getItemsByCategory(@NotNull String category) {
        return response.body().items().item().stream()
                .filter(item -> category.equals(item.category()))
                .sorted(Comparator.comparing(item -> item.baseDate() + item.baseTime()))
                .map(UltraSrtFcstItem::fcstValue)
                .sorted()
                .toList();
    }

    public record Response<T>(
            Header header,
            Body<T> body
    ) {

    }

    public record Header(
            String resultCode,
            String resultMsg
    ) {

    }

    public record Body<T>(
            String dataType,
            Items<T> items,
            int pageNo,
            int numOfRows,
            int totalCount
    ) {

    }

    public record Items<T>(
            List<T> item
    ) {

    }

    public record UltraSrtFcstItem(
            String baseDate,
            String baseTime,
            String category,
            String fcstDate,
            String fcstTime,
            String fcstValue,
            int nx,
            int ny
    ) {

    }

}
