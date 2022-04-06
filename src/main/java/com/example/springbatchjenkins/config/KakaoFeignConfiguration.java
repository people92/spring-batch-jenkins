package com.example.springbatchjenkins.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class KakaoFeignConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "KakaoAK 25ca90303af8723009e137e52babb4be");
    }
}
