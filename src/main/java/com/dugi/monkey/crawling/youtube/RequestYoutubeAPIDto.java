package com.dugi.monkey.crawling.youtube;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestYoutubeAPIDto {
    private String videoId;

    @Builder
    public RequestYoutubeAPIDto(String videoId) {
        this.videoId = videoId;
    }
}
