package com.dugi.monkey.service;

import com.dugi.monkey.domain.music.goodchart.GoodChart;
import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GoodChartService {

    private final GoodChartRepository goodChartRepository;

    @Transactional
    public Long addGoodChart(String goodVideoId) {
        return goodChartRepository.save(GoodChart.builder()
                                            .videoId(goodVideoId)
                                            .build())
                                   .getId();
    }

    @Transactional
    public void deleteGoodChart(String goodVideoId) {
        goodChartRepository.deleteBygoodVideoId(goodVideoId);
    }
}
