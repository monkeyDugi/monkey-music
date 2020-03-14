package com.dugi.monkey.domain.music.searchchart;

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
public class SearchChart extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String videoId;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, columnDefinition = "N")
    private String like;

    @Builder
    public SearchChart(String videoId, String title, String like, String image) {
        this.videoId = videoId;
        this.title = title;
        this.image = image;
        this.like = like;
    }
}
