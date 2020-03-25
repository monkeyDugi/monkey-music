package com.dugi.monkey.domain.music.goodchart;

import org.springframework.data.repository.query.Param;

public interface GoodChartRepositoryCustom {

    void deleteBygoodVideoId(@Param("goodVideoId") String goodVideoId);
}