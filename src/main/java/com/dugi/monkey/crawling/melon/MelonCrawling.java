package com.dugi.monkey.crawling.melon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MelonCrawling {

    public List<RequestMelonCrawlingDto> melonCrawilng() {
        String url = "https://www.melon.com/chart/day/index.htm?classCd=AB0000";
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Elements melons = doc.select("div.service_list_song>table>tbody>tr");

        List<RequestMelonCrawlingDto> requestMelonCrawlingDtos = new ArrayList<>();
        String rank;
        String title;
        String singer;
        String image;

        for (Element melon : melons) {
            rank = melon.select("span.rank").text();
            title = melon.select("div.wrap_song_info>div.rank01>span>a").text();
            singer = melon.select("div.wrap_song_info>div.rank02>span>a").text();
            image = melon.select("div>a.image_typeAll>img").attr("src");

            requestMelonCrawlingDtos.add(RequestMelonCrawlingDto.builder()
                                            .rank(rank)
                                            .title(title)
                                            .singer(singer)
                                            .image(image)
                                            .build());

            System.out.println(rank + title + singer + image);
        }

        return requestMelonCrawlingDtos;
    }
}
