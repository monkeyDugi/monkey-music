package com.dugi.monkey.crawling.youtube.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseYoutubeAPIDto {

    private String title;
    private String image;
    private String good;
    private String videoId;

    @Builder
    public ResponseYoutubeAPIDto(String title, String good, String image, String videoId) {
        this.title = title;
        this.good = good;
        this.image = image;
        this.videoId = videoId;
    }
}
