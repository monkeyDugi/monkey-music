package com.dugi.monkey.web.goodchart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseGoodChartDto {

    private String title;
    private String image;
    private String videoId;

    @Builder
    public ResponseGoodChartDto(String tile, String image, String videoId) {
        this.title = tile;
        this.image = image;
        this.videoId = videoId;
    }
}
