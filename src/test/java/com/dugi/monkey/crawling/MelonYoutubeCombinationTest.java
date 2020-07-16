package com.dugi.monkey.crawling;

import com.dugi.monkey.crawling.melon.MelonCrawling;
import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.APIKey;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import com.dugi.monkey.crawling.youtube.dto.ResponseMelonYoutubeCombinationDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.util.YoutubeConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MelonCrawling.class, YoutubeSearchAPI.class, MelonYoutubeCombination.class, APIKey.class, YoutubeConnection.class})
public class MelonYoutubeCombinationTest {

    private MelonYoutubeCombination melonYoutubeCombination;

    @Mock
    private MelonCrawling melonCrawling;

    @Mock
    private YoutubeSearchAPI youtubeSearchAPI;

    @Before
    public void mockYoutubeConnection() {
        MockitoAnnotations.initMocks(this);
        melonYoutubeCombination = new MelonYoutubeCombination(melonCrawling, youtubeSearchAPI);

        String rank = "1";
        String title = "sugar";
        String singer = "maroon5";
        String image = "image";
        String videoId = "videoId";

        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = new ArrayList<>();
        List<ResponseMelonCrawlingDto> responseMelonCrawlingDtos = new ArrayList<>();
        List<ResponseMelonYoutubeCombinationDto> responseMelonYoutubeCombinationDtos = new ArrayList<>();

        responseMelonCrawlingDtos.add(ResponseMelonCrawlingDto.builder()
                                                                .rank(rank)
                                                                .title(title)
                                                                .singer(singer)
                                                                .image(image)
                                                                .build());

        responseYoutubeAPIDtos.add(ResponseYoutubeAPIDto.builder()
                                                        .videoId(videoId)
                                                        .build());

        responseMelonYoutubeCombinationDtos.add(ResponseMelonYoutubeCombinationDto.builder()
                                                                                    .rank(rank)
                                                                                    .title(title)
                                                                                    .singer(singer)
                                                                                    .image(image)
                                                                                    .videoId(videoId)
                                                                                    .build());

        melonYoutubeCombination.setResponseMelonCrawlingDtos(responseMelonCrawlingDtos);
        melonYoutubeCombination.setResponseYoutubeAPIDtos(responseYoutubeAPIDtos);

//        given(melonCrawling.getCrawlingResult(1)).willReturn(responseMelonCrawlingDtos);
//        given(youtubeSearchAPI.getDailyChartApiResult(responseMelonCrawlingDtos ,1)).willReturn(responseYoutubeAPIDtos);
//        given(melonYoutubeCombination.getDailyChart()).willReturn(responseMelonYoutubeCombinationDtos);
//        given(melonYoutubeCombination.melonYou()).
    }

    // 실제 로직은 10개 이지만 로직이 도는지 확인만을 위한 테스트라서 1로 함
    @Test
    public void 멜론_일간차트를_YOUTUBE_API에서_가져온_size는_1이다() {
        assertThat(melonYoutubeCombination.runDailyChart().size()).isEqualTo(1);
    }
}