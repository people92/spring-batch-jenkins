package com.example.springbatchjenkins;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SampleJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SampleJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job basicJob() {
        return jobBuilderFactory.get("basicJob")
                .start(sampleStep2(null))
                .build();
    }
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet((contribution, chunkContext) -> {
//                    String parameter = (String) chunkContext.getStepContext().getJobParameters().get("date");
//                    log.info("job parameter : {}", parameter);
//                    log.info("sample step Test1");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
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
