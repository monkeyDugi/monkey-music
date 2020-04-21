package com.dugi.monkey.crawling.youtube;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YoutubeSearchAPI.class)
public class YoutubeSearchAPITest {

    YoutubeSearchAPI youtubeSearchAPI;

    // @MockBean이 없으면 안된다. 왜지?
    @MockBean
    RestTemplate restTemplate;

    String keyword1 = "김범수";
    String keyword2 = "보고싶다";
    int maxResult = 10;

    @Before
    public void setUp() {
        youtubeSearchAPI = new YoutubeSearchAPI(restTemplate);
    }

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
        mockUrl += "?key=" + APIKey.API_KEY.getApiKey();
        mockUrl += "&part=snippet&type=video&maxResults=" + maxResult;
        mockUrl += "&videoEmbeddable=true";
        mockUrl += "&q=" + keyword1;

        String url = youtubeSearchAPI.createUrl(maxResult);

        assertThat(url).isEqualTo(mockUrl);
    }
}