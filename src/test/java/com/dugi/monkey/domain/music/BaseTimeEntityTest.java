package com.dugi.monkey.domain.music;

import com.dugi.monkey.domain.music.goodchart.GoodChart;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTimeEntityTest {

    @Autowired
    GoodChartRepository goodChartRepository;

    @Before
    public void setUp() {
        goodChartRepository.deleteAll();
    }

    @Test
    public void BaseTimeEntity_등록() {

        // given
        LocalDateTime now = LocalDateTime.of(2020, 4, 23, 22, 24, 15);

        goodChartRepository.save(GoodChart.builder()
                                        .email("email")
                                        .videoId("videoId")
                                        .build());

        // when
        List<GoodChart> goodCharts = goodChartRepository.findAll();

        //then
        GoodChart goodChart = goodCharts.get(0);

        assertThat(goodChart.getCreateDate()).isAfter(now);
        assertThat(goodChart.getModifiedDate()).isAfter(now);
    }
}