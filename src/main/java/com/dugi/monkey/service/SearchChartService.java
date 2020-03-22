package com.dugi.monkey.service;

import com.dugi.monkey.crawling.dto.RequestSearchChartDto;
import com.dugi.monkey.crawling.youtube.searchchart.SearchChartYoutubeSearchAPIProcessing;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchChartService {

    private final GoodChartRepository goodChartRepository;

    @Autowired
    SearchChartYoutubeSearchAPIProcessing processing;

    @Transactional(readOnly = true)
    public List<ResponseSearchChartDto> getSearchChartAll(String word) {
        List<ResponseSearchChartDto> responseSearchChartDtos = new ArrayList<>();
        List<RequestSearchChartDto> requestSearchChartDtos = processing.searchChartSearchDataProcessing(word);

        String videoId;
        String goodWhether;

        for(int i = 0; i < requestSearchChartDtos.size(); i++) {
            videoId = requestSearchChartDtos.get(i).getVideoId();
            goodWhether = goodChartRepository.findMyListYN(videoId);

            responseSearchChartDtos.add(ResponseSearchChartDto.builder()
                                                            .requestSearchChartDto(requestSearchChartDtos.get(i))
                                                            .good(goodWhether.toString())
                                                            .build());
        }
        return responseSearchChartDtos;
    }
}
