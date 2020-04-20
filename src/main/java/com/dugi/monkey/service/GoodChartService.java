package com.dugi.monkey.service;

import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.goodchart.dto.ResponseGoodChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Long deleteByGoodVideoId(RequestGoodChartDto requestGoodChartDto) {
        return goodChartRepository.deleteByGoodVideoId(requestGoodChartDto);
    }

    @Transactional
    public Page<ResponseGoodChartDto> findByEmailGoodChart(String email, Pageable pageable) {
        return goodChartRepository.findByEmailGoodChart(email, pageable);
    }
}
