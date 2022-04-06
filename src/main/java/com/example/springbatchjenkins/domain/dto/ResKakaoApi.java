package com.example.springbatchjenkins.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResKakaoApi implements Serializable {

    public static ResKakaoApi EMPTY = new ResKakaoApi();

    List<Document> documents;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Document {
        private String contents;
        private String datetime;
        private String title;
        private String url;
    }

    public ResKakaoApi() {
        this.documents = new ArrayList<>();
    }
}
