package com.dugi.monkey.service;

import com.dugi.monkey.crawling.RequestDailyChartDto;
import com.dugi.monkey.domain.music.DailyChart;
import com.dugi.monkey.domain.music.DailyChartRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

public class DailyChartServiceTest {

    private DailyChartService dailyChartService;

    @Mock
    private DailyChartRepository dailyChartRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dailyChartService = new DailyChartService(dailyChartRepository);
    }

    @Test
    public void 일간차트_리스트_불러오기() {

        List<DailyChart> dailyChartsList = new ArrayList<>();
        dailyChartsList.add(DailyChart.builder()
                                        .rank("1")
                                        .videoId("videoId")
                                        .title("title")
                                        .singer("singer")
                                        .image("image")
                                        .build());

        given(dailyChartRepository.findAll()).willReturn(dailyChartsList);

        assertThat(dailyChartService.getDailyChartAll().get(0).getVideoId()).isEqualTo("videoId");
    }

    @Test
    public void 일간차트_등록() {
        List<RequestDailyChartDto> requestDailyChartDtos = new ArrayList<>();
        requestDailyChartDtos.add(RequestDailyChartDto.builder()
                                                        .rank("1")
                                                        .videoId("videoId")
                                                        .title("title")
                                                        .singer("singer")
                                                        .image("image")
                                                        .build());
        dailyChartService.addDailyChart(requestDailyChartDtos);

        verify(dailyChartRepository).save(any(DailyChart.class));
    }
}