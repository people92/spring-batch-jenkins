package com.example.springbatchjenkins.linkage.job;

import com.example.springbatchjenkins.domain.dto.ResKakaoApi;
import com.example.springbatchjenkins.linkage.client.KakaoOpenApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.*;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.springbatchjenkins.linkage.job.DaumSearchJobConfig.JOB_NAME;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "spring.batch.job.names", havingValue = JOB_NAME)
public class DaumSearchJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final KakaoOpenApiClient kakaoOpenApiClient;
    public static final String JOB_NAME = "daumSearchJob";
    private static final int chunkSize = 5;

    @Bean(JOB_NAME)
    public Job daumSearchJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(daumSearchJobStep(null))
                .build();
    }

    @Bean(JOB_NAME + "Step")
    @JobScope
    public Step daumSearchJobStep(@Value("#{jobParameters['date']}") String date) {
        return stepBuilderFactory.get(JOB_NAME + "Step")
                .<ResKakaoApi.Document, ResKakaoApi.Document>chunk(chunkSize)
                .reader(itemReader())
                .writer(itemWriter())
                .build();
    }
    @Bean(JOB_NAME + "Reader")
    @StepScope
    public ListItemReader<ResKakaoApi.Document> itemReader() {
        String query = "SSG.COM";
        ResKakaoApi resKakaoApi = kakaoOpenApiClient.searchDaumWeb(query);
        List<ResKakaoApi.Document> documentList = resKakaoApi.getDocuments();

        return new ListItemReader<>(documentList);
    }

    @Bean(JOB_NAME + "Writer")
    @StepScope
    public ItemWriter<ResKakaoApi.Document> itemWriter() {
        return list -> {
            for(ResKakaoApi.Document document : list) {
                log.info("Test : {}", document.getTitle());
            }
        };
    }


}
