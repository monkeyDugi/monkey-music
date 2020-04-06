package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.querydsl.core.Tuple;

import java.util.List;

public interface GoodChartRepositoryCustom {

    void deleteByGoodVideoId(RequestGoodChartDto requestGoodChartDto);
    Long findMyListExists(RequestGoodChartDto requestGoodChartDto);
    List<Tuple> findUserGoodChart(String email);
}