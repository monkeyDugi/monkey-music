package com.dugi.monkey.crawling;

import com.dugi.monkey.domain.music.dailychart.DailyChart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDailyChartDto {
    private String rank;
    private String title;
    private String singer;
    private String image;
    private String videoId;

    @Builder
    public RequestDailyChartDto(String rank, String title, String singer, String image, String videoId) {
        this.rank = rank;
        this.title = title;
        this.singer = singer;
        this.image = image;
        this.videoId = videoId;
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
