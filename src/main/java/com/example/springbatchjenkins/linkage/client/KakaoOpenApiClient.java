package com.example.springbatchjenkins.linkage.client;


import com.example.springbatchjenkins.config.KakaoFeignConfiguration;
import com.example.springbatchjenkins.domain.dto.ResKakaoApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakao",
        url = "https://dapi.kakao.com",
        configuration = KakaoFeignConfiguration.class
        )
public interface KakaoOpenApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v2/search/web")
    ResKakaoApi searchDaumWeb(@RequestParam(name = "query") String query);

}
