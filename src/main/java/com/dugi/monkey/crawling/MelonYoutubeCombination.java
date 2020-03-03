package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawilng;
import com.dugi.monkey.crawling.melon.RequestMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.RequestYoutubeAPIDto;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPIProcessing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MelonYoutubeCombination {

    public List<RequestDailyChartDto> dailyChart() {
        MelonCrawilng melonCrawilng = new MelonCrawilng();
        YoutubeSearchAPIProcessing processing = new YoutubeSearchAPIProcessing();

        List<RequestMelonCrawlingDto> requestMelonCrawlingDtoList = melonCrawilng.melonCrowilng();
        List<RequestYoutubeAPIDto> requestYoutubeAPIDtoList = processing.searchDataProcessing(requestMelonCrawlingDtoList);

        List<RequestDailyChartDto> requestDailyChartDtoList = new ArrayList<>();

        for (int i = 0; i < requestYoutubeAPIDtoList.size(); i++) {
            requestDailyChartDtoList.add(RequestDailyChartDto.builder()
                                            .rank(requestMelonCrawlingDtoList.get(i).getRank())
                                            .title(requestMelonCrawlingDtoList.get(i).getTitle())
                                            .singer(requestMelonCrawlingDtoList.get(i).getSinger())
                                            .image(requestMelonCrawlingDtoList.get(i).getImage())
                                            .videoId(requestYoutubeAPIDtoList.get(i).getVideoId())
                                            .build());
        }

        return requestDailyChartDtoList;
    }
}

