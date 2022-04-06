package com.example.springbatchjenkins.linkage.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.springbatchjenkins.linkage.job.SampleJobConfig.JOB_NAME;


@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.batch.job.names", havingValue = JOB_NAME)
public class SampleJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public static final String JOB_NAME = "sampleJob";

    @Bean
    public Job sampleJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(sampleStep2(null))
                .build();
    }

    @Bean
    @JobScope
    public Step sampleStep2(@Value("#{jobParameters['date']}") String date) {
        return stepBuilderFactory.get("sampleStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("job parameter : {}", date);
                    log.info("sample step Test2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
