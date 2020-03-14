package com.dugi.monkey.web.dailychart;

import com.dugi.monkey.domain.music.dailychart.DailyChart;
import com.dugi.monkey.service.DailyChartService;
import com.dugi.monkey.web.dailychart.DailyChartController;
import com.dugi.monkey.web.dailychart.dto.ResponseDailyChartDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DailyChartController.class)
public class DailyChartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DailyChartService dailyChartService;

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

        ResultActions actions = mockMvc.perform(get("/api/charts/daily")
                                                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                                        .andDo(print());

        actions.andExpect(status().isOk())
               .andExpect(jsonPath("[0].rank").value("1"))
               .andExpect(jsonPath("[0].videoId").value("videoId"))
               .andExpect(jsonPath("[0].title").value("title"))
               .andExpect(jsonPath("[0].singer").value("singer"))
               .andExpect(jsonPath("[0].image").value("image"));
    }
}