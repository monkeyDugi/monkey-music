package com.dugi.monkey.web.dailychart;

import com.dugi.monkey.service.DailyChartService;
import com.dugi.monkey.web.dailychart.dto.ResponseDailyChartDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * @author : 이병덕
 * @description : 일간차트 컨트롤러
 * @date : 2020.07.19 22:52:20
 */

@RequiredArgsConstructor
@RestController
public class DailyChartController {

    // @AutoWire를 쓰지 않고, @RequiredArgsConstructor로 생성자 주입
    private final DailyChartService dailyChartService;

    // 일간차트 전체 검색
    @GetMapping("api/charts/daily")
    public List<ResponseDailyChartDto> findDailyChartAll() {
        return dailyChartService.findDailyChartAll();
    }
}
