package com.dugi.monkey.service;

import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.domain.music.searchchart.SearchChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
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

    private List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos;
    private RequestGoodChartDto requestGoodChartDto;

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
        given(goodChartRepository.existsByVideoIdAndEmail(videoId, email)).willReturn(true);

        assertThat(searchChartService.isGood(videoId, email)).isEqualTo(true);

        verify(goodChartRepository).existsByVideoIdAndEmail(videoId, email);
    }

    @Test
    public void 마이리스트에_존재하지_않는_videoId() {
        given(goodChartRepository.existsByVideoIdAndEmail(videoId, email)).willReturn(false);

        assertThat(searchChartService.isGood(videoId, email)).isEqualTo(false);

        verify(goodChartRepository).existsByVideoIdAndEmail(videoId, email);
    }

    @Test
    public void 검색했던_차트일_경우() {
        given(searchChartRepository.existsByVideoId(videoId)).willReturn(true);

        assertThat(searchChartService.isSearch(videoId)).isEqualTo(true);

        verify(searchChartRepository).existsByVideoId(videoId);
    }

    @Test
    public void 검색했던_차트가_아닐_경우() {
        given(searchChartRepository.existsByVideoId(videoId)).willReturn(false);

        assertThat(searchChartService.isSearch(videoId)).isEqualTo(false);

        verify(searchChartRepository).existsByVideoId(videoId);
    }

    @Test
    public void 검색차트_반환() {
        given(youtubeSearchAPI.getSearchChartApiResult(word, 10)).willReturn(responseYoutubeAPIDtos);
        given(goodChartRepository.existsByVideoIdAndEmail(videoId, email)).willReturn(true);
        given(searchChartRepository.existsByVideoId(videoId)).willReturn(true);

        assertThat(searchChartService.findSearchChartAll(word, email)).isNotEmpty();
    }
}