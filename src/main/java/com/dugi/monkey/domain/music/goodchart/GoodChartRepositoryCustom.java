package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;

public interface GoodChartRepositoryCustom {

    void deleteByGoodVideoId(RequestGoodChartDto requestGoodChartDto);
}