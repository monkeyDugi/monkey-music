package com.dugi.monkey.service;

import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class GoodChartServiceTest {

    private GoodChartService goodChartService;

    private RequestGoodChartDto requestGoodChartDto;

    private String title = "maroon5";
    private String email = "test@test.com";
    private String videoId = "videoId";
    private String image = "image";

    @Mock
    private GoodChartRepository goodChartRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        goodChartService = new GoodChartService(goodChartRepository);

        requestGoodChartDto = RequestGoodChartDto.builder()
                .title(title)
                .email(email)
                .videoId(videoId)
                .image(image)
                .build();
    }

    @Test
    public void 마이리스트에_음악_등록() throws Exception{
        goodChartService.addGoodChart(requestGoodChartDto);

        verify(goodChartRepository).save(any());
    }

    @Test
    public void 마이리스트에서_음악_제거() throws Exception{
        goodChartService.deleteByGoodVideoId(requestGoodChartDto);

        verify(goodChartRepository).deleteByGoodVideoId(any());
    }
}