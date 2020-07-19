package com.dugi.monkey.web.dailychart.dto;

import com.dugi.monkey.domain.music.dailychart.DailyChart;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : 이병덕
 * @description : 일간차트 응답 DTO
 * @date : 2020.07.19 22:52:02
 */

@Getter
public class ResponseDailyChartDto {
    private String rank;
    private String title;
    private String singer;
    private String image;
    private String videoId;

    @Builder
    public ResponseDailyChartDto(DailyChart entity) {
        this.rank = entity.getRank();
        this.videoId = entity.getVideoId();
        this.title = entity.getTitle();
        this.singer = entity.getSinger();
        this.image = entity.getImage();
    }

    public DailyChart toEntity() {
        return DailyChart.builder()
                .rank(rank)
                .title(title)
                .singer(singer)
                .image(image)
                .videoId(videoId)
                .build();
    }
}
