package com.dugi.monkey.crawling.melon;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestMelonCrawlingDto {
    private String rank;
    private String title;
    private String singer;
    private String image;

    @Builder
    public RequestMelonCrawlingDto(String rank, String title, String singer, String image) {
        this.rank = rank;
        this.title = title;
        this.singer = singer;
        this.image = image;
    }
}
