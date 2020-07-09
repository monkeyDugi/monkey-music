package com.dugi.monkey.web.searchchart;

import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.scheduler.DailyChartScheduler;
import com.dugi.monkey.service.SearchChartService;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SearchChartControllerTest {

    // mocking하지 않으면 빈 생성 시 dailyChartJob을 생성하기 때문에 이미 존재한다는 에러 발생
    @MockBean
    private DailyChartScheduler dailyChartScheduler;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchChartService searchChartService;

    @Test
    @WithMockUser(roles = "USER")
    public void 검색차트를_가져온다() throws Exception {
        String word = "maroon5";
        String email = "test@test.com";
        String good = "Y";

        List<ResponseSearchChartDto> responseSearchChartDtos = new ArrayList<>();

        ResponseYoutubeAPIDto responseYoutubeAPIDto =
                                            ResponseYoutubeAPIDto.builder()
                                            .title("maroon5")
                                            .videoId("videoId")
                                            .image("image")
                                            .build();

        responseSearchChartDtos.add(ResponseSearchChartDto.builder().
                responseYoutubeAPIDto(responseYoutubeAPIDto)
                .good(good)
                .build());

        given(searchChartService.findSearchChartAll(word, email)).willReturn(responseSearchChartDtos);

        mockMvc.perform(get("/api/charts/search/" + word))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("maroon5")));

        verify(searchChartService).findSearchChartAll(word, email);
    }
}