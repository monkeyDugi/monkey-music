package com.dugi.monkey.crawling.melon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MelonCrawling.class)
public class MelonCrawlingTest {

    @Autowired
    private MelonCrawling melonCrawling;

    @Test
    public void 멜론_크롤링_결과는_NULL이_아니다() throws IOException {
        assertThat(melonCrawling.getMelonChartConnection()).isNotNull();
    }

    @Test
    public void 멜론_크롤링_결과의_일간차트는_100개_이다() throws IOException {
        assertThat(melonCrawling.getEntireElementsOfMelonChart().size()).isEqualTo(100);
    }

    @Test
    public void 멜론_크롤링_일간차트에서_10개를_추출한다() {
        assertThat(melonCrawling.getCrawlingResult(10).size()).isEqualTo(10);
    }
}