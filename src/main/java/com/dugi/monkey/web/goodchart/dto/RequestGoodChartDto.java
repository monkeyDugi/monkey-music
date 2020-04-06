package com.dugi.monkey.web.goodchart.dto;

import com.dugi.monkey.domain.music.goodchart.GoodChart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestGoodChartDto {

    private String videoId;
    private String email;
    private String image;
    private String title;

    @Builder
    public RequestGoodChartDto(String videoId, String email, String image, String title) {
        this.videoId = videoId;
        this.email = email;
        this.image = image;
        this.title = title;
    }

    public GoodChart toEntity() {
        return GoodChart.builder()
                        .videoId(videoId)
                        .email(email)
                        .build();
    }
}
