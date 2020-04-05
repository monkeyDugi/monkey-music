package com.dugi.monkey.domain.music.searchchart;

import com.dugi.monkey.domain.music.BaseTimeEntity;
import com.dugi.monkey.domain.music.goodchart.GoodChart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class SearchChart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String videoId;

    @Column(nullable = false)
    private String image;

    @Builder
    public SearchChart(String videoId, String title, String image) {
        this.videoId = videoId;
        this.title = title;
        this.image = image;
    }
}
