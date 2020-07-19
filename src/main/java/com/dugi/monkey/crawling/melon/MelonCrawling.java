package com.dugi.monkey.crawling.melon;

import com.dugi.monkey.crawling.melon.dto.ResponseMelonCrawlingDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : 이병덕
 * @description : 멜론에서 크롤링한 데이터를 응답 DTO에 리턴
 * @date : 2020.07.19 21:59:42
 */

@Component
public class MelonCrawling {

    // 멜론 일간차트 크롤링
    public List<ResponseMelonCrawlingDto> getCrawlingResult(int size) {
        List<ResponseMelonCrawlingDto> responseMelonCrawlingDtos = new ArrayList<>();
        
        String rank;
        String title;
        String singer;
        String image;

        try {
            Elements melonChartElements = getEntireElementsOfMelonChart();
            
            for(int i = 0; i < size; i++) {
                rank = melonChartElements.get(i).select("span.rank").text();
                title = melonChartElements.get(i).select("div.wrap_song_info>div.rank01>span>a").text();
                singer = melonChartElements.get(i).select("div.wrap_song_info>div.rank02>span>a").text();
                image = melonChartElements.get(i).select("div>a.image_typeAll>img").attr("src");

                responseMelonCrawlingDtos.add(ResponseMelonCrawlingDto.builder()
                        .rank(rank)
                        .title(title)
                        .singer(singer)
                        .image(image)
                        .build());
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }   

        return responseMelonCrawlingDtos;
    }

    protected Elements getEntireElementsOfMelonChart() throws IOException {
        Document doc = getMelonChartConnection();

        return doc.select("div.service_list_song>table>tbody>tr");
    }

    protected Document getMelonChartConnection() throws IOException {
        String url = "https://www.melon.com/chart/day/index.htm?classCd=AB0000";

        return Jsoup.connect(url).get();
    }
}
