package com.dugi.monkey.music.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MusicController {
    @GetMapping("/music/chart/daily")
    public String dailyList() {
        return "TEST";
    }
}
