package com.dugi.monkey.web.dto;

import com.dugi.monkey.domain.music.DailyCharts;
import lombok.Getter;

@Getter
public class ResponseDailyChartsDto {
    private int rank;
    private String videoId;
    private String title;
    private String singer;
    private String image;

    public ResponseDailyChartsDto(DailyCharts entity) {
            this.rank = entity.getRank();
            this.videoId = entity.getVideoId();
            this.title = entity.getTitle();
            this.singer = entity.getSinger();
            this.image = entity.getImage();
    }
}
