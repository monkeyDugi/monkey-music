package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.youtube.APIKey;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class MelonYoutubeCombinationTest {

    @Autowired
    private APIKey apiKey;

    private MelonYoutubeCombination melonYoutubeCombination = new MelonYoutubeCombination(new MelonCrawling(), new YoutubeSearchAPI(apiKey));

    @Test
    public void 멜론_일간차트를_YOUTUBE_API에서_가져온_size는_10이다() {
        assertThat(melonYoutubeCombination.getDailyChart().size()).isEqualTo(10);
    }
}