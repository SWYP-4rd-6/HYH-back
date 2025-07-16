package org.example.hyhbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job weatherDataJob;

    @Scheduled(cron = "0 15 2,5,8,11,14,17,20,23 * * *", zone = "Asia/Seoul")
    public void runWeatherDataJob() {
        log.info("Starting Weather Data Job at {}", LocalDateTime.now());
        for (int[] region : ALL_REGIONS) {
            LocalDateTime now = LocalDateTime.now();
            executeWeatherDataJob(now, region[0], region[1]);
        }
    }

    private void executeWeatherDataJob(LocalDateTime localDateTime, int nx, int ny) {
        String baseDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = determineBaseTime(localDateTime.getHour());

        log.info("Executing weather data job: {} {} nx={}, ny={}",
                baseDate, baseTime, nx, ny);
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("baseDate", baseDate)
                    .addString("baseTime", baseTime)
                    .addLong("nx", (long) nx)
                    .addLong("ny", (long) ny)
                    .toJobParameters();
            JobExecution jobExecution = jobLauncher.run(weatherDataJob, jobParameters);
            log.info("Weather data batch job execution completed. Status: {}, Exit Code: {}",
                    jobExecution.getStatus(),
                    jobExecution.getExitStatus().getExitCode());
        } catch (JobExecutionAlreadyRunningException e) {
            log.warn("Weather data batch job is already running", e);
        } catch (JobRestartException e) {
            log.error("Weather data batch job restart failed", e);
        } catch (JobInstanceAlreadyCompleteException e) {
            log.warn("Weather data batch job instance already completed", e);
        } catch (JobParametersInvalidException e) {
            log.error("Invalid job parameters for weather data batch", e);
        } catch (Exception e) {
            log.error("Unexpected error occurred while executing weather data batch job", e);
        }
    }

    private static int[][] ALL_REGIONS = {
            // 서울
            {60, 127}, {60, 126}
            /*{61, 127}, {62, 126}, {62, 128}, {61, 128}, {61, 129},
            {59, 127}, {58, 126}, {58, 125}, {59, 124}, {59, 125}, {61, 125}, {61, 126}*/
    };
/*    public static final int[][] ALL_REGIONS = {
            {60, 120}, {60, 121}, {60, 120}, {61, 121}, {61, 120}, {63, 124}, {63, 124}, {62, 123},
            {61, 130}, {59, 123}, {59, 123}, {57, 125}, {57, 125}, {57, 126}, {58, 125}, {62, 114},
            {61, 134}, {58, 121}, {57, 121}, {57, 128}, {56, 129}, {56, 129}, {60, 124}, {62, 127},
            {64, 128}, {62, 118}, {57, 123}, {59, 122}, {60, 122}, {64, 126}, {64, 119}, {62, 120},
            {62, 121}, {56, 131}, {68, 121}, {65, 115}, {55, 128}, {57, 119}, {65, 123}, {61, 131},
            {64, 134}, {71, 121}, {61, 138}, {69, 133}, {69, 125}, {60, 127}, {60, 127}, {60, 127},
            {60, 126}, {61, 127}, {62, 126}, {61, 127}, {62, 128}, {61, 127}, {61, 128}, {61, 129},
            {61, 129}, {59, 127}, {59, 127}, {59, 127}, {58, 126}, {58, 126}, {58, 125}, {59, 124},
            {58, 126}, {59, 125}, {59, 125}, {61, 125}, {61, 126}, {62, 126}, {62, 126}
    };*/

    private String determineBaseTime(int currentHour) {
        if (currentHour >= 23 || currentHour < 2) {
            return "2300";
        } else if (currentHour >= 20) {
            return "2000";
        } else if (currentHour >= 17) {
            return "1700";
        } else if (currentHour >= 14) {
            return "1400";
        } else if (currentHour >= 11) {
            return "1100";
        } else if (currentHour >= 8) {
            return "0800";
        } else if (currentHour >= 5) {
            return "0500";
        } else {
            return "0200";
        }
    }

}
