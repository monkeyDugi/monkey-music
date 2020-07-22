package com.dugi.monkey.domain.music.searchchart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchChartRepositoryTest {

    private SearchChart searchChart;
    String videoId = "videoId";

    @Autowired
    private SearchChartRepository searchChartRepository;

    @Before
    public void setUp() {
        searchChartRepository.deleteAll();

        // given
        searchChart
                = SearchChart.builder()
                .title("title")
                .image("image")
                .videoId(videoId)
                .build();
    }

    @Test
    public void 검색테이블에_존재하는_videoId() {
        // save
        searchChartRepository.save(searchChart);

        boolean result = searchChartRepository.existsByVideoId(videoId);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void 검색테이블에_존재하지_않는_videoId() {
        // save
        searchChartRepository.save(searchChart);

         boolean result = searchChartRepository.existsByVideoId("emptyVideoId");

        assertThat(result).isEqualTo(false);
    }
}