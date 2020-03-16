package com.dugi.monkey.crawling.youtube.dailychart;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class DailyChartYoutubeSearchAPIProcessingTest {

    @Autowired
    private DailyChartYoutubeSearchAPIProcessing dailyCahrtYoutubeSearchAPIProcessing;

    @Autowired
    private MelonCrawling melonCrawling;

    private List<RequestYoutubeAPIDto> requestYoutubeAPIDtos;

    @Before
    public void setUp() throws Exception {
        requestYoutubeAPIDtos = dailyCahrtYoutubeSearchAPIProcessing.dailyChartSearchDataProcessing(melonCrawling.melonCrawling());
    }

    @Test
    public void 유튜브_검색_결과가_1개가_맞는지() {
        assertThat(requestYoutubeAPIDtos.size()).isEqualTo(10);
    }
}