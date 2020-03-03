package com.dugi.monkey.web;

import com.dugi.monkey.service.DailyChartService;
import com.dugi.monkey.web.dto.ResponseDailyChartDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DailyChartController {
    // @AutoWire를 쓰지 않고, @RequiredArgsConstructor로 생성자 주입
    private final DailyChartService dailyChartService;

    @GetMapping("api/chart/daily")
    public List<ResponseDailyChartDto> list() {
        return dailyChartService.getDailyChart();
    }
}
