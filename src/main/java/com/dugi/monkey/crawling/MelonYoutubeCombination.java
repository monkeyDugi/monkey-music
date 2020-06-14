package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseMelonYoutubeCombinationDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MelonYoutubeCombination  {

    private final MelonCrawling melonCrawling;
    private final YoutubeSearchAPI youtubeSearchAPI;

    public List<ResponseMelonYoutubeCombinationDto> getDailyChart() {
        List<ResponseMelonCrawlingDto> responseMelonCrawlingDtos = melonCrawling.getCrawlingResult(10);
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = youtubeSearchAPI.getDailyChartApiResult(responseMelonCrawlingDtos, 1);

        List<ResponseMelonYoutubeCombinationDto> responseMelonYoutubeCombinationDtos = new ArrayList<>();

        for (int i = 0; i < responseYoutubeAPIDtos.size(); i++) {
            responseMelonYoutubeCombinationDtos.add(ResponseMelonYoutubeCombinationDto.builder()
                                            .rank(responseMelonCrawlingDtos.get(i).getRank())
                                            .title(responseMelonCrawlingDtos.get(i).getTitle())
                                            .singer(responseMelonCrawlingDtos.get(i).getSinger())
                                            .image(responseMelonCrawlingDtos.get(i).getImage())
                                            .videoId(responseYoutubeAPIDtos.get(i).getVideoId())
                                            .build());
        }

        return responseMelonYoutubeCombinationDtos;
    }
}

