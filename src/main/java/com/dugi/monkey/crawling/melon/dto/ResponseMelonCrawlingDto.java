package com.dugi.monkey.crawling.melon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author : 이병덕
 * @description : 멜론에서 긁어온 데이터 응답 DTO
 * @date : 2020.07.19 21:58:05
 */

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
