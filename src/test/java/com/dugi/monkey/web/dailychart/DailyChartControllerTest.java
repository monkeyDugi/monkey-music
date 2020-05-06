package com.dugi.monkey.web.dailychart;

import com.dugi.monkey.domain.music.dailychart.DailyChart;
import com.dugi.monkey.service.DailyChartService;
import com.dugi.monkey.web.dailychart.dto.ResponseDailyChartDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DailyChartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DailyChartService dailyChartService;

    @Test
    public void 일간차트를_가져온다() throws Exception {
        List<ResponseDailyChartDto> dailyChartDtos = new ArrayList<>();
        DailyChart dailyChart = DailyChart.builder()
                                            .rank("1")
                                            .videoId("videoId")
                                            .title("꾸러기")
                                            .singer("김범수&수란")
                                            .image("image")
                                            .build();

        dailyChartDtos.add(ResponseDailyChartDto.builder().entity(dailyChart).build());

        given(dailyChartService.findDailyChartAll()).willReturn(dailyChartDtos);

        mockMvc.perform(get("/api/charts/daily"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("김범수&수란")));

        verify(dailyChartService).findDailyChartAll();
    }
}