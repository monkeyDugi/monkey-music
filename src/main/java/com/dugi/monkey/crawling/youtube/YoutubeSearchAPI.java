package com.dugi.monkey.crawling.youtube;

import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import com.dugi.monkey.crawling.youtube.dto.ResponseYoutubeAPIDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class YoutubeSearchAPI {

    String keyword;
    private final static String API_URL = "https://www.googleapis.com/youtube/v3/search";
    private final static String API_KEY = "?key=" + APIKey.API_KEY.getApiKey();
    private final static String API_PARAMETER_PART_TYPE_MAXRESULT = "&part=snippet&type=video&maxResults=";
    private final static String API_PARAMETER_VIDEOEMBEDDABLE = "&videoEmbeddable=true";
    private final static String API_PARAMETER_KEYWORD = "&q=";

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

    public String createJson(int maxResult) {
        String jsonString = "";
        String inputLine = "";

        try {
            String url = createUrl(maxResult);
            HttpURLConnection con = setUrlMethod(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

            while ((inputLine = br.readLine()) != null) {
                jsonString += inputLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        return jsonString;
    }

    public String createUrl(int maxResult) throws IOException {

        return API_URL +
               API_KEY +
               API_PARAMETER_PART_TYPE_MAXRESULT + maxResult +
               API_PARAMETER_VIDEOEMBEDDABLE +
               API_PARAMETER_KEYWORD + URLEncoder.encode(keyword, "UTF-8");
    }

    public HttpURLConnection setUrlMethod(String url) throws IOException {
        /*
         * HttpURLConnection : 요청방식을 결정할 수 있다, 데이터 길이 제한이 없기 때문에 길이를 알 수 없는 데이터 요청 시 사용한다.
         *                     protected이기 때문에 직접 생성할 수 없고 openConnection() 리턴 값으로 캐스팅 해서 사용할 수 있다.
         */
        HttpURLConnection con = null;
        URL openUrl = new URL(url);
        con = (HttpURLConnection) openUrl.openConnection();
        con.setRequestMethod("GET");

        return con;
    }
}