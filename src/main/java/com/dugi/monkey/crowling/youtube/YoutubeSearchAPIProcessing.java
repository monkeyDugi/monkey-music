package com.dugi.monkey.crowling.youtube;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class YoutubeSearchAPIProcessing extends YoutubeSearchAPI{
    public List<RequestYoutubeAPIDto> searchDataProcessing() {
        List<RequestYoutubeAPIDto> requestYoutubeAPIDtoLIst = new ArrayList<>();

        try
        {
            String jsonString = searchData();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray items = jsonObject.getJSONArray("items");

            for(int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);

                JSONObject id = item.getJSONObject("id");
                String videoId = id.getString("videoId");

                JSONObject snippet = item.getJSONObject("snippet");

                String title = snippet.getString("title");

                JSONObject thumbnails = snippet.getJSONObject("thumbnails");

                JSONObject defaul = thumbnails.getJSONObject("default");

                String image = defaul.getString("url");

                requestYoutubeAPIDtoLIst.add(RequestYoutubeAPIDto.builder()
                                            .videoId(videoId)
                                            .title(title)
                                            .image(image)
                                            .build());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return requestYoutubeAPIDtoLIst;
    }
}
