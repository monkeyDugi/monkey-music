package com.dugi.monkey.domain.music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class DailyCharts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rank;

    @Column(nullable = false)
    private String videoId;

    @Column(nullable = false, length = 40)
    private String title;

    @Column(nullable = false, length = 20)
    private String singer;

    @Column(nullable = false)
    private String image;

    @CreatedDate
    private LocalDate createDate;

    @LastModifiedDate
    private LocalDate modifyDate;

    @Builder
    public DailyCharts(int rank, String videoId, String title, String singer, String image) {
        this.rank = rank;
        this.videoId = videoId;
        this.title = title;
        this.singer = singer;
        this.image = image;
    }
}
