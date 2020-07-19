package com.dugi.monkey.crawling.youtube.dto;

import com.dugi.monkey.domain.music.dailychart.DailyChart;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author : 이병덕
 * @description : 멜론에서 클롤링한 데이터와 해당 데이터로 youtube에서 검색한 데이터는 최종적으로 담는 응답 DTO
 * @date : 2020.07.19 22:00:27
 */

@ToString
@Getter
public class ResponseMelonYoutubeCombinationDto {
    private String rank;
    private String title;
    private String singer;
    private String image;
    private String videoId;

    @Builder
    public ResponseMelonYoutubeCombinationDto(String rank, String title, String singer, String image, String videoId) {
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
