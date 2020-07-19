package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseMelonYoutubeCombinationDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 이병덕
 * @description : 멜론에서 크롤링한 데이터로 youtube api에서 검하는 클래스
 * @date : 2020.07.19 22:06:13
 */

@Setter
@RequiredArgsConstructor
@Component
public class MelonYoutubeCombination  {

    private final MelonCrawling melonCrawling;
    private final YoutubeSearchAPI youtubeSearchAPI;

    // 멜론 해외 일간차트를 유튜브에서 검색
    public List<ResponseMelonYoutubeCombinationDto> getDailyChart() {
        List<ResponseMelonCrawlingDto> responseMelonCrawlingDtos = melonCrawling.getCrawlingResult(10);
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = youtubeSearchAPI.getDailyChartApiResult(responseMelonCrawlingDtos, 1);

        System.out.println(responseMelonCrawlingDtos);
        System.out.println(responseYoutubeAPIDtos);
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

