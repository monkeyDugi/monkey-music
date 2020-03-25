package com.dugi.monkey.service;

import com.dugi.monkey.crawling.dto.RequestSearchChartDto;
import com.dugi.monkey.crawling.youtube.searchchart.SearchChartYoutubeSearchAPIProcessing;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.domain.music.searchchart.SearchChartRepository;
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
    private final SearchChartRepository searchChartRepository;

    @Autowired
    SearchChartYoutubeSearchAPIProcessing processing;

    @Transactional
    public List<ResponseSearchChartDto> getSearchChartAll(String word) {
        List<ResponseSearchChartDto> responseSearchChartDtos = new ArrayList<>();
        List<RequestSearchChartDto> requestSearchChartDtos = processing.searchChartSearchDataProcessing(word);

        String videoId;
        String goodWhether;
        String chartWhether;

        for(int i = 0; i < requestSearchChartDtos.size(); i++) {
            videoId = requestSearchChartDtos.get(i).getVideoId();
            goodWhether = goodChartRepository.findMyListYN(videoId);

            responseSearchChartDtos.add(ResponseSearchChartDto.builder()
                                                            .requestSearchChartDto(requestSearchChartDtos.get(i))
                                                            .good(goodWhether.toString())
                                                            .build());

            chartWhether = searchChartRepository.findByVideoId(videoId);

            if("notExists".equals(chartWhether)) {
                searchChartRepository.save(responseSearchChartDtos.get(i).toEntity());
            }
        }
        return responseSearchChartDtos;
    }
}
