package com.dugi.monkey.domain.music.searchchart;

import org.springframework.data.repository.query.Param;

public interface SearchChartRepositoryCustom {

    Long findByExistsVideoId(@Param("videoId") String videoId);
}