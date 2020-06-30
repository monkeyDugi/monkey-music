package com.dugi.monkey.crawling.youtube;

import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YoutubeSearchAPITest {

    @Autowired
    private APIKey apiKey;

    private final YoutubeSearchAPI youtubeSearchAPI = new YoutubeSearchAPI(apiKey);

//    @Autowired
//    private YoutubeSearchAPI youtubeSearchAPI;


    private String keyword1 = "김범수";
    private String keyword2 = "보고싶다";
    private int maxResult10 = 10;
    private int maxResult1 = 1;

    @Test
    public void 검색어가_1개일_경우_그대로_반환한다() {
        youtubeSearchAPI.keywordJoin(keyword1);
        assertThat(youtubeSearchAPI.getKeyword()).isEqualTo(keyword1);
    }

    @Test
    public void 검색어가_2개_이상일_경우_결합하여_반환한다() {
        youtubeSearchAPI.keywordJoin(keyword1, keyword2);
        assertThat(youtubeSearchAPI.getKeyword()).isEqualTo(keyword1 + keyword2);
    }

    @Test
    public void YOUTUBE_SEARCH_API를_사용할_URL을_반환한다() {
        youtubeSearchAPI.keywordJoin(keyword1);

        String mockUrl = "https://www.googleapis.com/youtube/v3/search";
        mockUrl += "?key=" + apiKey.getYoutube();
        mockUrl += "&part=snippet&type=video&maxResults=" + maxResult10;
        mockUrl += "&videoEmbeddable=true";
        mockUrl += "&q=" + keyword1;

        String url = youtubeSearchAPI.createUrl(maxResult10);

        assertThat(url).isEqualTo(mockUrl);
    }

    @Test
    public void YOUTUBE_SEARCH_API의_결과는_NULL이_아니다() {
        youtubeSearchAPI.keywordJoin(keyword1);
        String json = youtubeSearchAPI.createJson(maxResult10);

        assertThat(json).isNotNull();
    }

    @Test
    public void YOUTUBE_SEARCH_API의_결과에_검색어가_포함된다() {
        youtubeSearchAPI.keywordJoin(keyword1);
        String json = youtubeSearchAPI.createJson(maxResult10);

        assertThat(json).contains(keyword1);
    }

    @Test
    public void YOUTUBE_SEARCH_API의_결과를_추출하여_List에_리턴한_Size는_10이다_검색기능() {
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = youtubeSearchAPI.getSearchChartApiResult(keyword1, maxResult10);

        assertThat(responseYoutubeAPIDtos.size()).isEqualTo(10);
    }

    @Test
    public void YOUTUBE_SEARCH_API의_결과를_추출하여_List에_리턴한_Size는_10이다_일간차트기능() {
        List<ResponseMelonCrawlingDto> requestMelonCrawlingDtos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            requestMelonCrawlingDtos.add(ResponseMelonCrawlingDto.builder()
                    .title(keyword2)
                    .singer(keyword1)
                    .build());
        }

        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = youtubeSearchAPI.getDailyChartApiResult(requestMelonCrawlingDtos, maxResult1);

        assertThat(responseYoutubeAPIDtos.size()).isEqualTo(10);
    }
}