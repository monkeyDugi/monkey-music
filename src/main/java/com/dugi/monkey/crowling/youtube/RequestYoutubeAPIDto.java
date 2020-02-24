package com.dugi.monkey.crowling.youtube;

import com.dugi.monkey.domain.music.DailyCharts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestYoutubeAPIDto {
    private String videoId;
    private String title;
    private String image;

    @Builder
    public RequestYoutubeAPIDto(String videoId, String title, String image) {
        this.videoId = videoId;
        this.title = title;
        this.image = image;
    }
}
