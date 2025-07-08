package io.hyh.hyhapplication.weather.infra.persistence;

import io.hyh.hyhapplication.weather.domain.WeatherData;
import io.hyh.hyhapplication.weather.domain.WeatherDataRepository;
import io.hyh.hyhapplication.weather.infra.persistence.repository.WeatherDataJpaRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class WeatherDataRepositoryAdapter implements WeatherDataRepository {

    private final WeatherDataJpaRepository weatherDataJpaRepository;


    @Override
    public @NotNull WeatherData save(@NotNull WeatherData weatherData) {
        return weatherDataJpaRepository.save(weatherData);
    }

    @Override
    public @NotNull List<WeatherData> getWeatherData(@NotNull String baseDate,
                                                     @NotNull List<String> fcstTimes,
                                                     @NotNull List<String> categories,
                                                     int nx, int ny) {
        return weatherDataJpaRepository.findSelectedFields(baseDate, fcstTimes, categories, nx, ny);
    }

    @Override
    public @NotNull List<WeatherData> getWeatherData(@NotNull String baseDate, @NotNull List<String> categories, int nx, int ny) {
        return weatherDataJpaRepository.findSelectedFields(baseDate, categories, nx, ny);
    }

}
