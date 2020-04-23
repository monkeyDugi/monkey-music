package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MelonYoutubeCombinationTest {

    MelonYoutubeCombination melonYoutubeCombination = new MelonYoutubeCombination(new MelonCrawling(), new YoutubeSearchAPI());

    @Test
    public void 멜론_일간차트를_YOUTUBE_API에서_가져온_size는_10이다() {
        assertThat(melonYoutubeCombination.getDailyChart().size()).isEqualTo(10);
    }
}