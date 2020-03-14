package com.dugi.monkey.crawling.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestSearchChartDto {

    private String title;
    private String image;
    private String like;
    private String videoId;

    @Builder
    public RequestSearchChartDto(String title, String like, String image, String videoId) {
        this.title = title;
        this.like = like;
        this.image = image;
        this.videoId = videoId;
    }
}
