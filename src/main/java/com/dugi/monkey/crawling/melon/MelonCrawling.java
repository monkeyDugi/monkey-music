package com.dugi.monkey.crawling.melon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MelonCrawling {
    
    public List<RequestMelonCrawlingDto> melonChartDataProcessing(int size) {
        List<RequestMelonCrawlingDto> requestMelonCrawlingDtos = new ArrayList<>();
        
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

                requestMelonCrawlingDtos.add(RequestMelonCrawlingDto.builder()
                        .rank(rank)
                        .title(title)
                        .singer(singer)
                        .image(image)
                        .build());
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }   

        return requestMelonCrawlingDtos;
    }

    public Elements getEntireElementsOfMelonChart() throws IOException {
        Document doc = getMelonChartConnection();

        return doc.select("div.service_list_song>table>tbody>tr");
    }

    public Document getMelonChartConnection() throws IOException {
        String url = "https://www.melon.com/chart/day/index.htm?classCd=AB0000";
        
        return Jsoup.connect(url).get();
    }
}
