package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.domain.music.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author : 이병덕
 * @description : 마이리스트
 * @date : 2020.07.19 22:12:37
 */

@Getter
@NoArgsConstructor
@Entity
public class GoodChart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String videoId;

    @Column(nullable = false)
    private String email;

    @Builder
    public GoodChart(String videoId, String email) {
        this.videoId = videoId;
        this.email = email;
    }
}
