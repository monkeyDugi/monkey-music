package com.dugi.monkey.web;

import com.dugi.monkey.service.DailyChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class mustacheController {

    private final DailyChartService dailyChartService;

    @GetMapping("/chart/daily")
    public String dailyChart(Model model) {
        return "dailyChart";
    }
}
