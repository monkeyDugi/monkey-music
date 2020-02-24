package com.dugi.monkey.crowling.melon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MelonCrowilng {
    public String melonData() {
        String url = "https://www.melon.com/chart/day/index.htm";
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Elements titels = doc.select("div.ellipsis>span>a");

        List<String> songs = new ArrayList<>();
        int x = 0;
        String song = "";
        for (Element e : titels) {
            song += e.text();
            x++;

            if (x > 1) {
                songs.add(song);
                x = 0;
                song = "";
            }
        }

        System.out.println(songs.get(0));
        return "ddd";
    }
}
