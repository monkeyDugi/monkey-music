package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.melon.RequestMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.RequestYoutubeAPIDto;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPIProcessing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MelonYoutubeCombination {

    private final MelonCrawling melonCrawling;
    private final YoutubeSearchAPIProcessing processing;

    public List<RequestDailyChartDto> dailyChart() {

        List<RequestMelonCrawlingDto> requestMelonCrawlingDtos = melonCrawling.melonCrawling();
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

