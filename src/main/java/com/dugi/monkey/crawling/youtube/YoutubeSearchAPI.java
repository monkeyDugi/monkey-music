package com.dugi.monkey.crawling.youtube;

import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class YoutubeSearchAPI {

//    @Autowired
//    private APIKey apiKey;

    private String keyword;
    private final static String API_URL = "https://www.googleapis.com/youtube/v3/search";
//    private String API_KEY = "?key=" + apiKey.getYoutube();
    private final static String API_PARAMETER_PART_TYPE_MAXRESULT = "&part=snippet&type=video&maxResults=";
    private final static String API_PARAMETER_VIDEOEMBEDDABLE = "&videoEmbeddable=true";
    private final static String API_PARAMETER_KEYWORD = "&q=";
    private RestTemplate restTemplate = new RestTemplate();

    public List<ResponseYoutubeAPIDto> getDailyChartApiResult(List<ResponseMelonCrawlingDto> requestMelonCrawlingDtos, int maxResult) {
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = new ArrayList<>();
        String title;
        String singer;

        for(int i = 0; i < 10; i++) {
            title = requestMelonCrawlingDtos.get(i).getTitle();
            singer = requestMelonCrawlingDtos.get(i).getSinger();

            keywordJoin(title, singer);

            String jsonString = null;
            jsonString = createJson(maxResult);

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

    public List<ResponseYoutubeAPIDto> getSearchChartApiResult(String word, int maxResult) {
        List<ResponseYoutubeAPIDto> responseYoutubeAPIDtos = new ArrayList<>();

        keywordJoin(word);

        String jsonString = null;
        jsonString = createJson(maxResult);

        for(int i = 0; i < 10; i++) {
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

    public void keywordJoin (String... keyword) {
        this.keyword = "";
        this.keyword =  String.join("", keyword);
    }

    // RestTemplate는 자동으로 UTF-8로 Encoding 하므로 인코딩을 직접 하면 옳지 않은 결과가 나옴.
    public String createJson(int maxResult) {
        return restTemplate.getForObject(createUrl(maxResult), String.class);
    }

    public String createUrl(int maxResult) {
        return API_URL +
//               API_KEY +
               API_PARAMETER_PART_TYPE_MAXRESULT + maxResult +
               API_PARAMETER_VIDEOEMBEDDABLE +
               API_PARAMETER_KEYWORD + keyword;
    }
}