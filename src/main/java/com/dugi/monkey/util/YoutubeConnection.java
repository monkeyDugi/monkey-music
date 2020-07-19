package com.dugi.monkey.util;

import com.dugi.monkey.crawling.youtube.APIKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author : 이병덕
 * @description : youtube api 연동
 * @date : 2020.07.19 22:50:26
 */

@Getter
@RequiredArgsConstructor
@Component
public class YoutubeConnection {

    private final APIKey apiKey;

    private String keyword;
    private String API_URL = "https://www.googleapis.com/youtube/v3/search";
    private String API_PARAMETER_PART_TYPE_MAXRESULT = "&part=snippet&type=video&maxResults=";
    private String API_PARAMETER_VIDEOEMBEDDABLE = "&videoEmbeddable=true";
    private String API_PARAMETER_KEYWORD = "&q=";

    private RestTemplate restTemplate = new RestTemplate();

    // youtube api에서 Json을 응답 받는다.
    // RestTemplate는 자동으로 UTF-8로 Encoding 하므로 인코딩을 직접 하면 옳지 않은 결과가 나옴.
    public String createJson(int maxResult) {
        return restTemplate.getForObject(createUrl(maxResult), String.class);
    }

    // youtube api를 사용하기 위한 url 생성
    public String createUrl(int maxResult) {
        return API_URL +
                "?key=" +
                apiKey.getYoutube() +
                API_PARAMETER_PART_TYPE_MAXRESULT + maxResult +
                API_PARAMETER_VIDEOEMBEDDABLE +
                API_PARAMETER_KEYWORD + keyword;
    }

    // youtube api사용을 위한 검색 키워드 가공
    public void keywordJoin (String... keyword) {
        this.keyword = "";
        this.keyword =  String.join("", keyword);
    }
}
