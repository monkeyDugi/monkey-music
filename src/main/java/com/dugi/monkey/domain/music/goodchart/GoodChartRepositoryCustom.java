package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.goodchart.dto.ResponseGoodChartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoodChartRepositoryCustom {

    Long deleteByGoodVideoId(RequestGoodChartDto requestGoodChartDto);
    Long findMyListExists(RequestGoodChartDto requestGoodChartDto);
    Page<ResponseGoodChartDto> findByEmailGoodChart(String email, Pageable pageable);
}