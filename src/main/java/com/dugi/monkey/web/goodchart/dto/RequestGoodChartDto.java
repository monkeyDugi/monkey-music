package com.dugi.monkey.web.goodchart.dto;

import com.dugi.monkey.domain.music.goodchart.GoodChart;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : 이병덕
 * @description : 마이리스트 요청 DTO
 * @date : 2020.07.19 22:52:36
 */

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
