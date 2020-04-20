package com.dugi.monkey.service;

import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.domain.music.searchchart.SearchChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchChartService {

    private final GoodChartRepository goodChartRepository;
    private final SearchChartRepository searchChartRepository;
    private final YoutubeSearchAPI youtubeSearchAPI;

    @Transactional
    public List<ResponseSearchChartDto> findSearchChartAll(String word, String email) {
        List<ResponseSearchChartDto> responseSearchChartDtos = new ArrayList<>();
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos;
        RequestGoodChartDto requestGoodChartDto;

        String videoId;
        Long goodExists;
        Long searchExists;

        responseYoutubeAPIDtos = getSearchCharts(word);

        for(int i = 0; i < responseYoutubeAPIDtos.size(); i++) {
            videoId = getVideoId(responseYoutubeAPIDtos, i);

            requestGoodChartDto = RequestGoodChartDto.builder()
                    .videoId(videoId)
                    .email(email)
                    .build();

            goodExists = isGood(requestGoodChartDto);

            responseSearchChartDtos.add(ResponseSearchChartDto.builder()
                    .responseYoutubeAPIDto(responseYoutubeAPIDtos.get(i))
                    .good(String.valueOf(goodExists))
                    .build());

            searchExists = isSearch(videoId);

            if(0 == searchExists) {
                searchChartRepository.save(responseSearchChartDtos.get(i).toEntity());
            }
        }

        return responseSearchChartDtos;
    }

    public List<ResponseYoutubeAPIDto> getSearchCharts(String word) {
        return youtubeSearchAPI.getSearchChartApiResult(word,10);
    }

    public String getVideoId(List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos, int index) {
        return responseYoutubeAPIDtos.get(index).getVideoId();
    }

    public Long isGood(RequestGoodChartDto requestGoodChartDto) {
        return goodChartRepository.findMyListExists(requestGoodChartDto);
    }

    public Long isSearch(String videoId) {
        return searchChartRepository.findByExistsVideoId(videoId);
    }
}