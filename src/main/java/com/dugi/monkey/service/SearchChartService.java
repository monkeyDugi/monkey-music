package com.dugi.monkey.service;

import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.domain.music.searchchart.SearchChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 이병덕
 * @description : 검색차트 검색
 * @date : 2020.07.19 22:24:57
 */

@RequiredArgsConstructor
@Service
public class SearchChartService {

    private final GoodChartRepository goodChartRepository;
    private final SearchChartRepository searchChartRepository;
    private final YoutubeSearchAPI youtubeSearchAPI;

    // youtube api로 검색차트를 검색하며, 검색 후 DB에 존재하지 않으면, DB에 등록
    // 검색차트 DB에 저장은 하지만, 사용하지는 않는다.
    // youtube api 절약을 위해 이미 검색했던 videoId이면 검색 DB에서 가져오는 로직을 만들고 싶은데, 결국 videoId를 가져오기 위해서는
    // youtube api를 사용해야 하기 때문에 의미가 없기 때문에, 개선 사항임    @Transactional
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

    // youtube api를 통해 키워드로 10개의 차트 검색
    protected List<ResponseYoutubeAPIDto> getSearchCharts(String word) {
        return youtubeSearchAPI.getSearchChartApiResult(word,10);
    }

    // youtube api를 통해 가져온 데이터 중 videoId만 추출
    protected String getVideoId(List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos, int index) {
        return responseYoutubeAPIDtos.get(index).getVideoId();
    }

    // 마이 리스트에 videoId, email가 존재하는지 counting
    // videoId, email에 대한 중복 데이터는 없으므로 0 or 1
    protected Long isGood(RequestGoodChartDto requestGoodChartDto) {
        return goodChartRepository.findMyListExists(requestGoodChartDto);
    }

    // 검색차트에 videoId에 대한 데이터가 있는지 counting
    // 중복 데이터는 없으므로 0 or 1
    protected Long isSearch(String videoId) {
        return searchChartRepository.findByExistsVideoId(videoId);
    }
}