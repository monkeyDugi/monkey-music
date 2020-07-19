package com.dugi.monkey.crawling.youtube.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * @author : 이병덕
 * @description : youtube api를 통해 검색해온 데이터 응답 DTO
 * @date : 2020.07.19 22:01:20
 */

@ToString
@Getter
public class ResponseYoutubeAPIDto {

    private String title;
    private String image;
    private String good;
    private String videoId;

    @Builder
    public ResponseYoutubeAPIDto(String title, String good, String image, String videoId) {
        this.title = title;
        this.good = good;
        this.image = image;
        this.videoId = videoId;
    }
}
