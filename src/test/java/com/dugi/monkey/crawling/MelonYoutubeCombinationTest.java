package com.dugi.monkey.crawling;

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
public class MelonYoutubeCombinationTest {

    @Autowired
    private MelonYoutubeCombination melonYoutubeCombination;

    private List<RequestDailyChartDto> requestDailyChartDtos;

    @Before
    public void setUp() {
        requestDailyChartDtos = melonYoutubeCombination.dailyChart();
    }

    @Test
    public void 멜론_일간차트_유튜브_결과가_1개인지() {
        assertThat(requestDailyChartDtos.size()).isEqualTo(1);
    }
}