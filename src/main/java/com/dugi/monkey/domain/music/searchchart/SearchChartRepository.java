package com.dugi.monkey.domain.music.searchchart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SearchChartRepository extends JpaRepository<SearchChart, Long> {

    @Query(value = "SELECT 'exists' FROM DUAL WHERE EXISTS (SELECT 1 FROM SEARCH_CHART g WHERE g.VIDEO_ID =:videoId)" +
            "UNION " +
            "SELECT 'notExists' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM SEARCH_CHART g WHERE g.VIDEO_ID =:videoId)", nativeQuery = true)
    String findByVideoId(@Param("videoId") String videoId);
}
