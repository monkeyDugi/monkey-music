package com.dugi.monkey.domain.music.dailychart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class DailyChartRepositoryTest {

    @Autowired
    DailyChartRepository dailyChartRepository;

    @Before
    public void cleanup() {
        dailyChartRepository.deleteAll();
    }

    @Test
    public void 일간차트_불러오기() {
        // given
        dailyChartRepository.save(DailyChart.builder()
                .rank("1")
                .videoId("videoId")
                .title("title")
                .singer("singer")
                .image("image")
                .build());

        // when
        List<DailyChart> dailyCharts = dailyChartRepository.findAll();

        // then
        DailyChart dailyChart = dailyCharts.get(0);
        assertThat(dailyChart.getRank()).isEqualTo("1");
        assertThat(dailyChart.getVideoId()).isEqualTo("videoId");
        assertThat(dailyChart.getTitle()).isEqualTo("title");
        assertThat(dailyChart.getSinger()).isEqualTo("singer");
        assertThat(dailyChart.getImage()).isEqualTo("image");
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.now();

        dailyChartRepository.save(DailyChart.builder()
                .rank("1")
                .videoId("videoId")
                .title("title")
                .singer("singer")
                .image("image")
                .build());

        // when
        DailyChart dailyChart = dailyChartRepository.findAll().get(0);

        // then
        System.out.println(">>>>>>>>>>>>>> createDate = " + dailyChart.getCreateDate() + ", modifiedDate = " + dailyChart.getModifiedDate());
        assertThat(dailyChart.getCreateDate()).isAfter(now);
        assertThat(dailyChart.getModifiedDate()).isAfter(now);
    }
}
