package com.dugi.monkey.service;

import com.dugi.monkey.crawling.dto.RequestSearchChartDto;
import com.dugi.monkey.crawling.youtube.searchchart.SearchChartYoutubeSearchAPIProcessing;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.domain.music.searchchart.SearchChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
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
    public List<ResponseSearchChartDto> getSearchChartAll(String word, String email) {
        List<ResponseSearchChartDto> responseSearchChartDtos = new ArrayList<>();
        List<RequestSearchChartDto> requestSearchChartDtos = processing.searchChartSearchDataProcessing(word);

        String videoId;
        Long goodExists;
        Long chartExists;

        for(int i = 0; i < requestSearchChartDtos.size(); i++) {
            videoId = requestSearchChartDtos.get(i).getVideoId();

            RequestGoodChartDto requestGoodChartDto = RequestGoodChartDto.builder()
                                                                            .videoId(videoId)
                                                                            .email(email)
                                                                            .build();
            goodExists = goodChartRepository.findMyListExists(requestGoodChartDto);

            responseSearchChartDtos.add(ResponseSearchChartDto.builder()
                                                            .requestSearchChartDto(requestSearchChartDtos.get(i))
                                                            .good(String.valueOf(goodExists))
                                                            .build());

            chartExists = searchChartRepository.findByExistsVideoId(videoId);

            if(0 == chartExists) {
                searchChartRepository.save(responseSearchChartDtos.get(i).toEntity());
            }
        }
        return responseSearchChartDtos;
    }
}
