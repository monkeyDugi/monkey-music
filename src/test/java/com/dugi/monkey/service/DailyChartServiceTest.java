package com.dugi.monkey.service;

import com.dugi.monkey.domain.music.dailychart.DailyChart;
import com.dugi.monkey.domain.music.dailychart.DailyChartRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class DailyChartServiceTest {

//    private DailyChartService dailyChartService;
//
//    @Mock
//    private DailyChartRepository dailyChartRepository;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        dailyChartService = new DailyChartService(dailyChartRepository);
//    }
//
//    @Test
//    public void 검색차트를_가져온다() {
//        List<DailyChart> dailyChartDtos = new ArrayList<>();
//        DailyChart dailyChart = DailyChart.builder()
//                .rank("1")
//                .videoId("videoId")
//                .title("꾸러기")
//                .singer("김범수&수란")
//                .image("image")
//                .build();
//
//        dailyChartDtos.add(dailyChart);
//
//
//        given(dailyChartRepository.findAll()).willReturn(dailyChartDtos);
//
//        assertThat(dailyChartService.findDailyChartAll()).isNotNull();
//
//        verify(dailyChartRepository).findAll();
//    }
}