package com.dugi.monkey.crawling.youtube.dailychart;

import com.dugi.monkey.crawling.melon.RequestMelonCrawlingDto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DailyChartYoutubeSearchAPIProcessing extends DailyChartYoutubeSearchAPI {

    public List<RequestYoutubeAPIDto> dailyChartSearchDataProcessing(List<RequestMelonCrawlingDto> requestMelonCrawlingDtos) {

        List<RequestYoutubeAPIDto> requestYoutubeAPIDtos = new ArrayList<>();

        try
        {
            for(int i = 0; i < 10; i++) {
                String jsonString = dailyChartYoutubeSearchAPI(requestMelonCrawlingDtos.get(i));
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
