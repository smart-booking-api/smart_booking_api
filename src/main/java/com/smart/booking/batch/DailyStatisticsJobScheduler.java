package com.smart.booking.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class DailyStatisticsJobScheduler {

    private final JobLauncher jobLauncher;
    private final Job simpleJob;

    @Autowired
    public DailyStatisticsJobScheduler(JobLauncher jobLauncher, Job simpleJob) {
        this.jobLauncher = jobLauncher;
        this.simpleJob = simpleJob;
    }

    @Scheduled(cron = "0/2 * * * * *")
    public void runSimpleJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
            jobLauncher.run(simpleJob, jobParameters);
            log.info("Job executed successfully");
        } catch (Exception e) {
            log.error("Job execution failed", e);
        }
    }
}