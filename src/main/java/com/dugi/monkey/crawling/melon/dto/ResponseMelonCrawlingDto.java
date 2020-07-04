package com.dugi.monkey.crawling.melon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ResponseMelonCrawlingDto {
    private String rank;
    private String title;
    private String singer;
    private String image;

    @Builder
    public ResponseMelonCrawlingDto(String rank, String title, String singer, String image) {
        this.rank = rank;
        this.title = title;
        this.singer = singer;
        this.image = image;
    }
}
