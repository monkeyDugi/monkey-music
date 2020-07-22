package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.goodchart.dto.ResponseGoodChartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : 이병덕
 * @description : 마이리스트 queryDsl
 * @date : 2020.07.19 22:13:50
 */

public interface GoodChartRepositoryCustom {

    Page<ResponseGoodChartDto> findByEmailGoodChart(String email, Pageable pageable);
}