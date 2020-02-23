package com.dugi.monkey.youtube;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class YoutubeSearchAPI {
    public static void main(String[] args) {
        String jsonString = "{'age':20, 'name':'jjjj', 'student2':{'age':21,'name':'galid'}}";

        try
        {
            JSONObject jsonObject = new JSONObject(jsonString);
            System.out.println(jsonObject.getJSONObject("student2"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            String apiurl = "https://www.googleapis.com/youtube/v3/search";
//            apiurl += "?key=AIzaSyDGNqrzytyG4vieS-Pa1daow3onXPH62TQ";					//api 키
//            apiurl += "&part=snippet&type=video&maxResults=2&videoEmbeddable=true";	//max result 최대 20개 그 이상이면 할당량 더 사라짐.
//            apiurl += "&q=" + URLEncoder.encode("on방탄소년단","UTF-8");					//검색어 한글깨짐 방지.
//
//            /*
//             * HttpURLConnection : 요청방식을 결정할 수 있다, 데이터 길이 제한이 없기 때문에 길이를 알 수 없는 데이터 요청 시 사용한다.
//             *                     protected이기 때문에 직접 생성할 수 없고 openConnection() 리턴 값으로 캐스팅 해서 사용할 수 있다.
//             */
//            URL url = new URL(apiurl);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
//
//            String inputLine;
//            while((inputLine = br.readLine()) != null) {
//
//                System.out.print(inputLine + "\n");
//            }
//
//            br.close();
//        }
//        catch(Exception ex) {
//
//            System.out.print(ex.getMessage());
//        }
    }
}
