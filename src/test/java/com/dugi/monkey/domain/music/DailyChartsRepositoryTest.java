package com.dugi.monkey.domain.music;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyChartsRepositoryTest {
    @Autowired
    DailyChartsRepository dailyChartsRepository;

    @Before
    public void cleanup() {
        dailyChartsRepository.deleteAll();
    }

    @Test
    public void 일간차트_불러오기() {
        // given
        String rank = "12";
        String videoId = "UuV2BmJ1p_I";
        String title = "아무노래";
        String singer = "지코";
        String image = "https://i.ytimg.com/vi/UuV2BmJ1p_I/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLB5nudwhD7aBZkTSH4IFhbWNei-7Q";

        dailyChartsRepository.save(DailyCharts.builder()
                .rank(rank)
                .videoId(videoId)
                .title(title)
                .singer(singer)
                .image(image)
                .build());

        // when
        List<DailyCharts> dailyChartsList = dailyChartsRepository.findAll();

        // then
        DailyCharts dailyCharts = dailyChartsList.get(0);
        assertThat(dailyCharts.getRank()).isEqualTo(rank);
        assertThat(dailyCharts.getVideoId()).isEqualTo(videoId);
        assertThat(dailyCharts.getTitle()).isEqualTo(title);
        assertThat(dailyCharts.getSinger()).isEqualTo(singer);
        assertThat(dailyCharts.getImage()).isEqualTo(image);
    }
}
