package com.example.springbatchjenkins;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchJenkinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchJenkinsApplication.class, args);
    }

}
