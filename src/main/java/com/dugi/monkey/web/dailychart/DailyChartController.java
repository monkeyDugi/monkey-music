package com.dugi.monkey.web.dailychart;

import com.dugi.monkey.service.DailyChartService;
import com.dugi.monkey.web.dailychart.dto.ResponseDailyChartDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DailyChartController {

    // @AutoWire를 쓰지 않고, @RequiredArgsConstructor로 생성자 주입
    private final DailyChartService dailyChartService;

    @GetMapping("api/charts/daily")
    public List<ResponseDailyChartDto> list() {
        return dailyChartService.getDailyChartAll();
    }
}
