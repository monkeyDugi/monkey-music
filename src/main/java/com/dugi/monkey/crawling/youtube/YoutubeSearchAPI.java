package com.dugi.monkey.crawling.youtube;

import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import com.dugi.monkey.util.YoutubeConnection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Component
public class YoutubeSearchAPI {

    private final YoutubeConnection youtubeConnection;

    // 멜론에서 가져온 일간차트를 유튜브에서 검색
    public List<ResponseYoutubeAPIDto> getDailyChartApiResult(List<ResponseMelonCrawlingDto> requestMelonCrawlingDtos, int maxResult) {
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = new ArrayList<>();
        String title;
        String singer;

        // 멜론에서 가져온 검색어의 갯수만큼 조회하며, 그 개수는 10개
        for(int i = 0; i < requestMelonCrawlingDtos.size(); i++) {
            title = requestMelonCrawlingDtos.get(i).getTitle();
            singer = requestMelonCrawlingDtos.get(i).getSinger();

            youtubeConnection.keywordJoin(title, singer);

            String jsonString = null;
            jsonString = youtubeConnection.createJson(maxResult);

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject item = items.getJSONObject(0);
            JSONObject id = item.getJSONObject("id");

            String videoId = id.getString("videoId");

            responseYoutubeAPIDtos.add(ResponseYoutubeAPIDto.builder()
                    .videoId(videoId)
                    .build());
        }

        return responseYoutubeAPIDtos;
    }

    // 검색 키워드로 유튜브에서 검색
    public List<ResponseYoutubeAPIDto> getSearchChartApiResult(String word, int maxResult) {
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = new ArrayList<>();

        youtubeConnection.keywordJoin(word);

        String jsonString = null;
        jsonString = youtubeConnection.createJson(maxResult);

        for(int i = 0; i < maxResult; i++) {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject item = items.getJSONObject(i);

            JSONObject id = item.getJSONObject("id");
            JSONObject snippet = item.getJSONObject("snippet");
            JSONObject thumbnails = snippet.getJSONObject("thumbnails");
            JSONObject def = thumbnails.getJSONObject("default");

            String videoId = id.getString("videoId");
            String title = snippet.getString("title");
            String image = def.getString("url");

            responseYoutubeAPIDtos.add(ResponseYoutubeAPIDto.builder()
                    .title(title)
                    .image(image)
                    .videoId(videoId)
                    .build());
        }

        return responseYoutubeAPIDtos;
    }
}