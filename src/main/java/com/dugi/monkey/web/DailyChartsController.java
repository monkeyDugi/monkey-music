package com.dugi.monkey.web;

import com.dugi.monkey.domain.music.service.DailyChartsService;
import com.dugi.monkey.web.dto.DailyChartsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DailyChartsController {
    // @AutoWire를 쓰지 않고, @RequiredArgsConstructor로 생성자 주입
    private final DailyChartsService dailyChartsService;

    @GetMapping("api/charts/daily")
    public List<DailyChartsResponseDto> list() {
        return dailyChartsService.findAll();
    }
}
