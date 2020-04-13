package com.dugi.monkey.crawling.youtube.dailychart;

import com.dugi.monkey.crawling.dto.RequestDailyChartDto;
import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.melon.RequestMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.dailychart.RequestYoutubeAPIDto;
import com.dugi.monkey.crawling.youtube.dailychart.DailyChartYoutubeSearchAPIProcessing;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MelonYoutubeCombination {

    private final MelonCrawling melonCrawling;
    private final DailyChartYoutubeSearchAPIProcessing processing;

    public List<RequestDailyChartDto> dailyChart() {

        List<RequestMelonCrawlingDto> requestMelonCrawlingDtos = melonCrawling.melonChartDataProcessing(10);
        List<RequestYoutubeAPIDto> requestYoutubeAPIDtos = processing.dailyChartSearchDataProcessing(requestMelonCrawlingDtos);

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

