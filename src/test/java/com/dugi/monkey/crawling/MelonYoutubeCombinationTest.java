package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.youtube.APIKey;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {MelonCrawling.class, YoutubeSearchAPI.class, MelonYoutubeCombination.class, APIKey.class})
public class MelonYoutubeCombinationTest {

//    @Autowired
//    private YoutubeSearchAPI youtubeSearchAPI;
////
//    @Autowired
//    private MelonCrawling melonCrawling;

//    @Autowired
//    private MelonYoutubeCombination melonYoutubeCombination;
//
//    @Test
//    public void 멜론_일간차트를_YOUTUBE_API에서_가져온_size는_10이다() {
////        assertThat(melonYoutubeCombination.getDailyChart().size()).isEqualTo(10);
//        assertThat(melonYoutubeCombination.runDailyChartTest().size()).isEqualTo(10);
//    }
}