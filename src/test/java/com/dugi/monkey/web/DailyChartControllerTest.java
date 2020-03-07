package com.dugi.monkey.web;

import com.dugi.monkey.domain.music.DailyChart;
import com.dugi.monkey.scheduler.DailyChartScheduler;
import com.dugi.monkey.service.DailyChartService;
import com.dugi.monkey.web.dto.ResponseDailyChartDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.hamcrest.Matchers.containsString;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DailyChartController.class)
public class DailyChartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DailyChartService dailyChartService;

    @MockBean
    private DailyChartScheduler dailyChartScheduler;

    @Test
    public void 일간차트_리스트_불러오기() throws Exception {
        List<ResponseDailyChartDto> responseDailyChartDtos = new ArrayList<>();

        responseDailyChartDtos.add(ResponseDailyChartDto.builder()
                                                        .entity(DailyChart.builder()
                                                                        .rank("1")
                                                                        .videoId("videoId")
                                                                        .title("title")
                                                                        .singer("singer")
                                                                        .image("image")
                                                                        .build())
                                                        .build());

        given(dailyChartService.getDailyChartAll()).willReturn(responseDailyChartDtos);

        mockMvc.perform(get("/api/charts/daily"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("title")));

        verify(dailyChartService).getDailyChartAll();
    }
}