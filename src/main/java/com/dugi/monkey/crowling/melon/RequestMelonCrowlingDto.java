package com.dugi.monkey.crowling.melon;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestMelonCrowlingDto {
    private String rank;
    private String title;
    private String singer;
    private String image;

    @Builder
    public RequestMelonCrowlingDto(String rank, String title, String singer, String image) {
        this.rank = rank;
        this.title = title;
        this.singer = singer;
        this.image = image;
    }
}
