package com.dugi.monkey.web.goodchart.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : 이병덕
 * @description : 마이리스트 응답 DTO
 * @date : 2020.07.19 22:52:54
 */

@Getter
public class ResponseGoodChartDto {

    private String title;
    private String image;
    private String videoId;

    @Builder
    @QueryProjection // 쿼리 결과가 자동 매핑 되도록 해준다.
    public ResponseGoodChartDto(String tile, String image, String videoId) {
        this.title = tile;
        this.image = image;
        this.videoId = videoId;
    }
}
