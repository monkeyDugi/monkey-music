package com.dugi.monkey.crawling.melon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MelonCrawlingTest {

    @Autowired
    private MelonCrawling melonCrawling;
    private List<RequestMelonCrawlingDto> melonCrawlingDtos;

    @Before
    public void setUp() {
        melonCrawlingDtos = melonCrawling.melonCrawling();
    }

    @Test
    public void 멜론_일간차트_TOP_100_크롤링_데이터가_100개인지() {
        assertThat(melonCrawlingDtos.size()).isEqualTo(100);
    }
}
