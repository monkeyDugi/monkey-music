package com.dugi.monkey.crowling;

import com.dugi.monkey.crowling.melon.MelonCrowilng;
import com.dugi.monkey.crowling.youtube.RequestYoutubeAPIDto;
import com.dugi.monkey.crowling.youtube.YoutubeSearchAPIProcessing;

public class MainTest {
    public static void main(String[] args) {
        YoutubeSearchAPIProcessing processiong = new YoutubeSearchAPIProcessing();

        RequestYoutubeAPIDto requestYoutubeAPIDto = processiong.searchDataProcessing().get(1);

        System.out.println(processiong.searchDataProcessing().size());

        MelonCrowilng melonCrowilng = new MelonCrowilng();
        System.out.println(melonCrowilng.melonData());
    }
}

