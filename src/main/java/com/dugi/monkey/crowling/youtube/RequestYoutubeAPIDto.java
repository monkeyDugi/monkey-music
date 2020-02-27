package com.dugi.monkey.crowling.youtube;

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
