package com.dugi.monkey.domain.music.searchchart;

import org.springframework.data.repository.query.Param;

/**
 * @author : 이병덕
 * @description : 검색차트 queryDsl
 * @date : 2020.07.19 22:16:29
 */

public interface SearchChartRepositoryCustom {

    Long findByExistsVideoId(@Param("videoId") String videoId);
}