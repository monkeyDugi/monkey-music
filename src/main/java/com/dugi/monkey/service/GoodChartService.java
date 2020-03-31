package com.dugi.monkey.service;

import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GoodChartService {

    private final GoodChartRepository goodChartRepository;

    @Transactional
    public void addGoodChart(RequestGoodChartDto requestGoodChartDto) {
        goodChartRepository.save(requestGoodChartDto.toEntity());
    }

    @Transactional
    public void deleteGoodChart(RequestGoodChartDto requestGoodChartDto) {
        goodChartRepository.deleteByGoodVideoId(requestGoodChartDto);
    }
}
