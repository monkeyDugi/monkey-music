package com.dugi.monkey.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mustacheController {

    @GetMapping("/charts/daily")
    public String dailyChart(Model model) {
        return "dailyChart";
    }

    @GetMapping("/charts/search")
    public String searchMusic(Model model) {
        return "searchChart";
    }
}
