package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.melon.RequestMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.RequestYoutubeAPIDto;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPIProcessing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MelonYoutubeCombination {

    public List<RequestDailyChartDto> dailyChart() {
        MelonCrawling melonCrawling = new MelonCrawling();
        YoutubeSearchAPIProcessing processing = new YoutubeSearchAPIProcessing();

        List<RequestMelonCrawlingDto> requestMelonCrawlingDtos = melonCrawling.melonCrawilng();
        List<RequestYoutubeAPIDto> requestYoutubeAPIDtos = processing.searchDataProcessing(requestMelonCrawlingDtos);

        List<RequestDailyChartDto> requestDailyChartDtos = new ArrayList<>();

        for (int i = 0; i < requestYoutubeAPIDtos.size(); i++) {
            requestDailyChartDtos.add(RequestDailyChartDto.builder()
                                            .rank(requestMelonCrawlingDtos.get(i).getRank())
                                            .title(requestMelonCrawlingDtos.get(i).getTitle())
                                            .singer(requestMelonCrawlingDtos.get(i).getSinger())
                                            .image(requestMelonCrawlingDtos.get(i).getImage())
                                            .videoId(requestYoutubeAPIDtos.get(i).getVideoId())
                                            .build());
        }

        return requestDailyChartDtos;
    }
}

