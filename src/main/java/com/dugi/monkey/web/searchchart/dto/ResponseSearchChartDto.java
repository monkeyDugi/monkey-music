package com.dugi.monkey.web.searchchart.dto;

import com.dugi.monkey.crawling.dto.RequestSearchChartDto;
import com.dugi.monkey.domain.music.dailychart.DailyChart;
import com.dugi.monkey.domain.music.searchchart.SearchChart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseSearchChartDto {

    private String title;
    private String image;
    private String good;
    private String videoId;

    @Builder
    public ResponseSearchChartDto(RequestSearchChartDto requestSearchChartDto, String good) {
        this.title = requestSearchChartDto.getTitle();
        this.image = requestSearchChartDto.getImage();
        this.videoId = requestSearchChartDto.getVideoId();
        this.good = good;
    }

    public SearchChart toEntity() {
        return SearchChart.builder()
                .title(title)
                .image(image)
                .videoId(videoId)
                .build();
    }
}
