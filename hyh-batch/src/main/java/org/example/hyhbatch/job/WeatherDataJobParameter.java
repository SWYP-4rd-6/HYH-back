package org.example.hyhbatch.job;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class WeatherDataJobParameter {

    @Value("#{jobParameters[baseDate]}")
    private String baseDate;

    @Value("#{jobParameters[baseTime]}")
    private String baseTime;

    @Value("#{jobParameters[nx]}")
    private int nx;

    @Value("#{jobParameters[ny]}")
    private int ny;

}
