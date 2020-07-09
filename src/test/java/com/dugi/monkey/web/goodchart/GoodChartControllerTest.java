package com.dugi.monkey.web.goodchart;

import com.dugi.monkey.scheduler.DailyChartScheduler;
import com.dugi.monkey.service.GoodChartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GoodChartControllerTest {

    @MockBean
    private DailyChartScheduler dailyChartScheduler;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodChartService goodChartService;

    String goodVideoId;

    @Before
    public void setUp() {
        goodVideoId = "videoId";
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 마이리스트에_음악_등록() throws Exception {
        mockMvc.perform(post("/api/charts/good/" + goodVideoId))
                .andExpect(status().isOk());

        verify(goodChartService).addGoodChart(any());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void 마이리스트에서_음악_제거() throws Exception {
        mockMvc.perform(delete("/api/charts/good/" + goodVideoId))
                .andExpect(status().isOk());

        verify(goodChartService).deleteByGoodVideoId(any());
    }
}