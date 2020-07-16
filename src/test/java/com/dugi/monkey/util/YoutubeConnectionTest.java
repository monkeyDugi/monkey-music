package com.dugi.monkey.util;

import com.dugi.monkey.crawling.youtube.APIKey;
import com.dugi.monkey.crawling.youtube.YoutubeSearchAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
// 필수로 사용할 클래스만 지정 해주자. 의존관계로 인한 테스트가 어려워 진다.
@SpringBootTest(classes = {APIKey.class, YoutubeConnection.class})
public class YoutubeConnectionTest {

    @Autowired
    private YoutubeConnection youtubeConnection;

    private String keyword1 = "maroon5";
    private String keyword2 = "sugar";
    private int maxResult1 = 1;

    @Test
    public void 검색어가_1개일_경우_그대로_반환한다() {
        youtubeConnection.keywordJoin(keyword1);
        assertThat(youtubeConnection.getKeyword()).isEqualTo(keyword1);
    }

    @Test
    public void 검색어가_2개_이상일_경우_결합하여_반환한다() {
        youtubeConnection.keywordJoin(keyword1, keyword2);
        assertThat(youtubeConnection.getKeyword()).isEqualTo(keyword1 + keyword2);
    }

    @Test
    public void YOUTUBE_SEARCH_API를_사용할_URL을_반환한다() {
        youtubeConnection.keywordJoin(keyword1);

        String mockUrl = "https://www.googleapis.com/youtube/v3/search";
        mockUrl += "?key=" + youtubeConnection.getApiKey().getYoutube();
        mockUrl += "&part=snippet&type=video&maxResults=" + maxResult1;
        mockUrl += "&videoEmbeddable=true";
        mockUrl += "&q=" + keyword1;

        String url = youtubeConnection.createUrl(maxResult1);

        assertThat(url).isEqualTo(mockUrl);
    }

    @Test
    public void YOUTUBE_SEARCH_API의_결과는_NULL이_아니다() {
        youtubeConnection.keywordJoin(keyword1);
        String json = youtubeConnection.createJson(maxResult1);

        assertThat(json).isNotNull();
    }

    @Test
    public void YOUTUBE_SEARCH_API의_결과에_검색어가_포함된다() {
        youtubeConnection.keywordJoin(keyword1);
        String json = youtubeConnection.createJson(maxResult1);

        assertThat(json).contains(keyword1);
    }
}