package com.dugi.monkey.web.goodchart.dto;

import com.dugi.monkey.domain.music.goodchart.GoodChart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestGoodChartDto {

    private String videoId;
    private String email;

    @Builder
    public RequestGoodChartDto(String videoId, String email) {
        this.videoId = videoId;
        this.email = email;
    }

    public GoodChart toEntity() {
        return GoodChart.builder()
                        .videoId(videoId)
                        .email(email)
                        .build();
    }
}
