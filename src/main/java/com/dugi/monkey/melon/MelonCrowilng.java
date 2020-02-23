package com.dugi.monkey.melon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MelonCrowilng {

//    public static void main(String[] args) throws IOException {
//        String url = "https://www.melon.com/chart/day/index.htm";
//        Document doc = Jsoup.connect(url).get();
//
//        Elements titels = doc.select("div.ellipsis>span>a");
//
//        List<String> songs = new ArrayList<>();
//        int i = 0;
//        String song = "";
//        for(Element e : titels) {
//            song += e.text();
//            i++;
//
//            if(i > 1) {
//                songs.add(song);
//                i = 0;
//                song = "";
//            }
//        }
//
//        System.out.println(songs.get(0));
//    }
}
