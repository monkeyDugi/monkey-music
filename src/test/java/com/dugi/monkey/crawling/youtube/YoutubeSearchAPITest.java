package com.dugi.monkey.crawling.youtube;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.util.YoutubeConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
// 필수로 사용할 클래스만 지정 해주자. 의존관계로 인한 테스트가 어려워 진다.
@SpringBootTest(classes = {YoutubeSearchAPI.class, APIKey.class,  YoutubeConnection.class})
public class YoutubeSearchAPITest {

    String testCreatJson = "{\n" +
            "  \"kind\": \"youtube#searchListResponse\",\n" +
            "  \"etag\": \"huu6h6JNGH056RzF70MvT_IYPhU\",\n" +
            "  \"nextPageToken\": \"CAEQAA\",\n" +
            "  \"regionCode\": \"KR\",\n" +
            "  \"pageInfo\": {\n" +
            "    \"totalResults\": 1000000,\n" +
            "    \"resultsPerPage\": 1\n" +
            "  },\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"kind\": \"youtube#searchResult\",\n" +
            "      \"etag\": \"lO7N375cMwcj8PcIKDCB9XCoiK8\",\n" +
            "      \"id\": {\n" +
            "        \"kind\": \"youtube#video\",\n" +
            "        \"videoId\": \"SlPhMPnQ58k\"\n" +
            "      },\n" +
            "      \"snippet\": {\n" +
            "        \"publishedAt\": \"2019-10-08T14:00:10Z\",\n" +
            "        \"channelId\": \"UCN1hnUccO4FD5WfM7ithXaw\",\n" +
            "        \"title\": \"Maroon 5 - Memories (Official Video)\",\n" +
            "        \"description\": \"Memories” is out now: https://smarturl.it/MemoriesMaroon5 For more, visit: https://www.facebook.com/maroon5 https://twitter.com/maroon5 ...\",\n" +
            "        \"thumbnails\": {\n" +
            "          \"default\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/SlPhMPnQ58k/default.jpg\",\n" +
            "            \"width\": 120,\n" +
            "            \"height\": 90\n" +
            "          },\n" +
            "          \"medium\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/SlPhMPnQ58k/mqdefault.jpg\",\n" +
            "            \"width\": 320,\n" +
            "            \"height\": 180\n" +
            "          },\n" +
            "          \"high\": {\n" +
            "            \"url\": \"https://i.ytimg.com/vi/SlPhMPnQ58k/hqdefault.jpg\",\n" +
            "            \"width\": 480,\n" +
            "            \"height\": 360\n" +
            "          }\n" +
            "        },\n" +
            "        \"channelTitle\": \"Maroon5VEVO\",\n" +
            "        \"liveBroadcastContent\": \"none\",\n" +
            "        \"publishTime\": \"2019-10-08T14:00:10Z\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "\n" +
            "\n" +
            "Process finished with exit code 0\n";

    private YoutubeSearchAPI youtubeSearchAPI;

    @Mock
    private YoutubeConnection youtubeConnection;

    @Before
    public void mockYoutubeConnection() {
        MockitoAnnotations.initMocks(this);
        youtubeSearchAPI = new YoutubeSearchAPI(youtubeConnection);

        given(youtubeConnection.createJson(maxResult1)).willReturn(testCreatJson);
    }

    private String keyword1 = "maroon5";
    private String keyword2 = "sugar";
    private int maxResult1 = 1;

    @Test
    public void YOUTUBE_SEARCH_API의_결과를_추출하여_List에_리턴한_Size는_maxResult이다_검색기능() {
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = youtubeSearchAPI.getSearchChartApiResult(keyword1, maxResult1);

        assertThat(responseYoutubeAPIDtos.size()).isEqualTo(1);
    }

    @Test
    public void YOUTUBE_SEARCH_API의_결과를_추출하여_List에_리턴한_Size는_멜론에서_가져온_개수이다_일간차트기능() {
        List<ResponseMelonCrawlingDto> requestMelonCrawlingDtos = new ArrayList<>();

        requestMelonCrawlingDtos.add(ResponseMelonCrawlingDto.builder()
                .title(keyword2)
                .singer(keyword1)
                .build());

        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = youtubeSearchAPI.getDailyChartApiResult(requestMelonCrawlingDtos, maxResult1);

        assertThat(responseYoutubeAPIDtos.size()).isEqualTo(1);
    }
}