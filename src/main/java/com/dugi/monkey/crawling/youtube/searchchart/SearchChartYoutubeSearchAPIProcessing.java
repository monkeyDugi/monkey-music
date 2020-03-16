package com.dugi.monkey.crawling.youtube.searchchart;

import com.dugi.monkey.crawling.dto.RequestSearchChartDto;
import com.dugi.monkey.crawling.youtube.dailychart.RequestYoutubeAPIDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchChartYoutubeSearchAPIProcessing extends SearchChartYoutubeSearchAPI {

    public List<RequestSearchChartDto> searchChartSearchDataProcessing(String word) {
        List<RequestSearchChartDto> requestSearchChartDtos = new ArrayList<>();

        try
        {
            for(int i = 0; i < 10; i++) {
                String jsonString = searchChartyoutubeSearchAPI(word);
//                System.out.println(jsonString);
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

                requestSearchChartDtos.add(RequestSearchChartDto.builder()
                        .title(title)
                        .image(image)
                        .videoId(videoId)
                        .build());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestSearchChartDtos;
    }
}
