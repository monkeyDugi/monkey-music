package com.dugi.monkey.web.goodchart;

import com.dugi.monkey.service.GoodChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GoodChartController {

    private final GoodChartService goodChartService;

    @PostMapping("api/charts/good/{goodVideoId}")
    public Long addGoodChart(@PathVariable("goodVideoId") String goodVideoId) {
        return goodChartService.addGoodChart(goodVideoId);
    }

    @DeleteMapping("api/charts/good/{goodVideoId}")
    public void deleteGoodChart(@PathVariable("goodVideoId") String goodVideoId) {
        goodChartService.deleteGoodChart(goodVideoId);
    }
}
