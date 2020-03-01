package com.dugi.monkey.crowling;

import com.dugi.monkey.crowling.melon.MelonCrowilng;
import com.dugi.monkey.crowling.melon.RequestMelonCrowlingDto;
import com.dugi.monkey.crowling.youtube.RequestYoutubeAPIDto;
import com.dugi.monkey.crowling.youtube.YoutubeSearchAPIProcessing;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MelonYoutubeCombination {

    public List<RequestDailyChartsDto> dailyCharts() {
        MelonCrowilng melonCrowilng = new MelonCrowilng();
        YoutubeSearchAPIProcessing processiong = new YoutubeSearchAPIProcessing();

        List<RequestMelonCrowlingDto> requestMelonCrowlingDtoList = melonCrowilng.melonCrowilng();
        List<RequestYoutubeAPIDto> requestYoutubeAPIDtoList = processiong.searchDataProcessing(requestMelonCrowlingDtoList);

        List<RequestDailyChartsDto> requestDailyChartsDtoList = new ArrayList<>();

        for(int i = 0; i < requestYoutubeAPIDtoList.size(); i++) {
            requestDailyChartsDtoList.add(RequestDailyChartsDto.builder()
                                            .rank(requestMelonCrowlingDtoList.get(i).getRank())
                                            .title(requestMelonCrowlingDtoList.get(i).getTitle())
                                            .singer(requestMelonCrowlingDtoList.get(i).getSinger())
                                            .image(requestMelonCrowlingDtoList.get(i).getImage())
                                            .videoId(requestYoutubeAPIDtoList.get(i).getVideoId())
                                            .build());
        }

        return requestDailyChartsDtoList;
    }
}

