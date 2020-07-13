package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseMelonYoutubeCombinationDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MelonYoutubeCombination  {

    private final MelonCrawling melonCrawling;
    private final YoutubeSearchAPI youtubeSearchAPI;

    private List<ResponseMelonCrawlingDto> responseMelonCrawlingDtos;
    private List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos;


    // PostConstruct 살려야할지 말아야 할지?

//    @PostConstruct
    public List<ResponseMelonYoutubeCombinationDto> runDailyChart() {
        melonYou();

        return getDailyChart();
    }

    //    @PostConstruct
    protected void melonYou() {
        responseMelonCrawlingDtos = melonCrawling.getCrawlingResult(10);
        responseYoutubeAPIDtos = youtubeSearchAPI.getDailyChartApiResult(responseMelonCrawlingDtos, 1);
    }

    protected List<ResponseMelonYoutubeCombinationDto> getDailyChart() {
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

