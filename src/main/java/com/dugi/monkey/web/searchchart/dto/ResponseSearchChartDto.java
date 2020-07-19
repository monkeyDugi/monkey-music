package com.dugi.monkey.web.searchchart.dto;

import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.domain.music.searchchart.SearchChart;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : 이병덕
 * @description : 검색차트 응답 DTO
 * @date : 2020.07.19 22:55:16
 */

@Getter
public class ResponseSearchChartDto {

    private String title;
    private String image;
    private String good;
    private String videoId;

    @Builder
    public ResponseSearchChartDto(ResponseYoutubeAPIDto responseYoutubeAPIDto, String good) {
        this.title = responseYoutubeAPIDto.getTitle();
        this.image = responseYoutubeAPIDto.getImage();
        this.videoId = responseYoutubeAPIDto.getVideoId();
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
