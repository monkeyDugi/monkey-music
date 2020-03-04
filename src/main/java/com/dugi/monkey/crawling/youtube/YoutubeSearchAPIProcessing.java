package com.dugi.monkey.crawling.youtube;

import com.dugi.monkey.crawling.melon.RequestMelonCrawlingDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YoutubeSearchAPIProcessing extends YoutubeSearchAPI{

    public List<RequestYoutubeAPIDto> searchDataProcessing(List<RequestMelonCrawlingDto> requestMelonCrawlingDtos) {

        List<RequestYoutubeAPIDto> requestYoutubeAPIDtos = new ArrayList<>();

        try
        {
            for(int i = 0; i < 1; i++) {
                String jsonString = youtubeSearchAPI(requestMelonCrawlingDtos.get(i));
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray items = jsonObject.getJSONArray("items");
                JSONObject item = items.getJSONObject(0);
                JSONObject id = item.getJSONObject("id");
                String videoId = id.getString("videoId");

                requestYoutubeAPIDtos.add(RequestYoutubeAPIDto.builder()
                        .videoId(videoId)
                        .build());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestYoutubeAPIDtos;
    }
}
