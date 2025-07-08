package org.example.hyhbatch.job;

import io.hyh.hyhapplication.weather.domain.WeatherData;
import io.hyh.hyhapplication.weather.domain.WeatherDataRepository;
import io.hyh.hyhapplication.weather.infra.apiclient.weather.VilageForecastApiClient;
import io.hyh.hyhapplication.weather.infra.apiclient.weather.VilageForecastRequest;
import io.hyh.hyhapplication.weather.infra.apiclient.weather.VillageForecastItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hyhbatch.reader.HttpRequestReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WeatherDataJobConfiguration {

    private static final String JOB_NAME = "weatherDataJob";
    private static final String STEP_NAME = "weatherDataStep";


    @Bean(JOB_NAME)
    public Job weatherJob(JobRepository jobRepository,
                          Step weatherStep) {
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(weatherStep)
                .build();
    }

    @Bean(STEP_NAME)
    @JobScope
    public Step weatherStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            ItemReader<VillageForecastItem> weatherItemReader,
                            ItemWriter<VillageForecastItem> weatherItemWriter) {
        return new StepBuilder(STEP_NAME, jobRepository)
                .listener(new RunIdIncrementer())
                .<VillageForecastItem, VillageForecastItem>chunk(100, transactionManager)
                .reader(weatherItemReader)
                .writer(weatherItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<VillageForecastItem> weatherItemReader(VilageForecastApiClient vilageForecastApiClient,
                                                             WeatherDataJobParameter jobParameter) {
        return new HttpRequestReader<>((httpPageRequest -> vilageForecastApiClient.getVillageForecast(
                new VilageForecastRequest(
                        httpPageRequest.getPageNo(),
                        httpPageRequest.getPageSize(),
                        "json",
                        jobParameter.getBaseDate(),
                        jobParameter.getBaseTime(),
                        jobParameter.getNx(),
                        jobParameter.getNy()
                )
        )), 1000);
    }

    @Bean
    public ItemWriter<VillageForecastItem> weatherItemWriter(WeatherDataRepository weatherDataRepository) {
        return items -> {
            for (VillageForecastItem item : items) {
                weatherDataRepository.save(WeatherData.builder()
                        .baseDate(item.baseDate())
                        .baseTime(item.baseTime())
                        .fcstDate(item.fcstDate())
                        .fcstTime(item.fcstTime())
                        .fcstValue(item.fcstValue())
                        .category(item.category())
                        .nx(item.nx())
                        .ny(item.ny())
                        .dataType("getVilageFcst")
                        .build());
            }
        };
    }

    @Bean
    @JobScope
    public WeatherDataJobParameter jobParameter() {
        return new WeatherDataJobParameter();
    }

}
