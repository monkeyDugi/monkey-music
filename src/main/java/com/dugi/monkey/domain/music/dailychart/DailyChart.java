package com.dugi.monkey.domain.music.dailychart;

import com.dugi.monkey.domain.music.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class DailyChart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rank;

    @Column(nullable = false, unique = true)
    private String videoId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String singer;

    @Column(nullable = false)
    private String image;

    @Builder
    public DailyChart(String rank, String videoId, String title, String singer, String image) {
        this.rank = rank;
        this.videoId = videoId;
        this.title = title;
        this.singer = singer;
        this.image = image;
    }
}
