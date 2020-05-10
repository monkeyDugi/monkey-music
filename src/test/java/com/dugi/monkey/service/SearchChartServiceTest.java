package com.dugi.monkey.service;

import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.domain.music.searchchart.SearchChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SearchChartServiceTest {

    private SearchChartService searchChartService;

    @Mock
    private SearchChartRepository searchChartRepository;
    @Mock
    private GoodChartRepository goodChartRepository;
    @Mock
    private YoutubeSearchAPI youtubeSearchAPI;

    List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos;
    RequestGoodChartDto requestGoodChartDto;

    String title = "title";
    String videoId = "videoId";
    String image = "image";
    String email = "test@test.com";
    String word = "maroon5";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        searchChartService = new SearchChartService(goodChartRepository, searchChartRepository, youtubeSearchAPI);

        responseYoutubeAPIDtos = new ArrayList<>();
        responseYoutubeAPIDtos.add(ResponseYoutubeAPIDto.builder()
                .title(title)
                .videoId(videoId)
                .image(image)
                .build());

        requestGoodChartDto =
                RequestGoodChartDto.builder()
                        .videoId(videoId)
                        .email(email)
                        .build();
    }

    @Test
    public void Youtube_검색결과() {
        given(youtubeSearchAPI.getSearchChartApiResult(word, 10)).willReturn(responseYoutubeAPIDtos);

        assertThat(searchChartService.getSearchCharts(word)).isEqualTo(responseYoutubeAPIDtos);

        verify(youtubeSearchAPI).getSearchChartApiResult(word, 10);
    }

    @Test
    public void Youtube_검색결과에서_videoId_추출() {
        assertThat(searchChartService.getVideoId(responseYoutubeAPIDtos, 0)).isEqualTo(videoId);
    }

    @Test
    public void 마이리스트에_존재하는_videoId() {
        given(goodChartRepository.findMyListExists(requestGoodChartDto)).willReturn(1L);

        assertThat(searchChartService.isGood(requestGoodChartDto)).isEqualTo(1L);

        verify(goodChartRepository).findMyListExists(requestGoodChartDto);
    }

    @Test
    public void 마이리스트에_존재하지_않는_videoId() {
        given(goodChartRepository.findMyListExists(requestGoodChartDto)).willReturn(0L);

        assertThat(searchChartService.isGood(requestGoodChartDto)).isEqualTo(0L);

        verify(goodChartRepository).findMyListExists(requestGoodChartDto);
    }

    @Test
    public void 검색했던_차트일_경우() {
        given(searchChartRepository.findByExistsVideoId(videoId)).willReturn(1L);

        assertThat(searchChartService.isSearch(videoId)).isEqualTo(1L);

        verify(searchChartRepository).findByExistsVideoId(videoId);
    }

    @Test
    public void 검색했던_차트가_아닐_경우() {
        given(searchChartRepository.findByExistsVideoId(videoId)).willReturn(0L);

        assertThat(searchChartService.isSearch(videoId)).isEqualTo(0L);

        verify(searchChartRepository).findByExistsVideoId(videoId);
    }

    @Test
    public void 검색차트_반환() {
        given(youtubeSearchAPI.getSearchChartApiResult(word, 10)).willReturn(responseYoutubeAPIDtos);
        given(goodChartRepository.findMyListExists(requestGoodChartDto)).willReturn(0L);
        given(searchChartRepository.findByExistsVideoId(videoId)).willReturn(0L);

        assertThat(searchChartService.findSearchChartAll(word, email)).isNotEmpty();
    }
}