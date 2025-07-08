package io.hyh.hyhapplication.weather.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface WeatherDataRepository {

    @NotNull WeatherData save(@NotNull WeatherData weatherData);

    @NotNull List<WeatherData> getWeatherData(@NotNull String baseDate,
                                              @NotNull List<String> fcstTimes,
                                              @NotNull List<String> categories,
                                              int nx, int ny);

    @NotNull List<WeatherData> getWeatherData(@NotNull String baseDate,
                                              @NotNull List<String> categories,
                                              int nx, int ny);

}
