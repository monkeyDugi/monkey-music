package com.dugi.monkey.web;

import com.dugi.monkey.domain.music.service.DailyChartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class mustacheController {
    private final DailyChartsService dailyChartsService;

    @GetMapping("/charts/daily")
    public String dailyCharts(Model model) {
//        model.addAttribute("dailyCharts", dailyChartsService.findAllDesc());

        return "dailyCharts";
    }
}
