package com.dugi.monkey.crawling.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestSearchChartDto {

    private String title;
    private String image;
    private String good;
    private String videoId;

    @Builder
    public RequestSearchChartDto(String title, String good, String image, String videoId) {
        this.title = title;
        this.good = good;
        this.image = image;
        this.videoId = videoId;
    }
}
