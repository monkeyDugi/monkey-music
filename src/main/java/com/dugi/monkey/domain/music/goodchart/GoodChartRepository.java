package com.dugi.monkey.domain.music.goodchart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoodChartRepository extends JpaRepository<GoodChart, Long> {

    @Query(value = "SELECT 'Y' FROM DUAL WHERE EXISTS (SELECT 1 FROM GOOD_CHART g WHERE g.VIDEO_ID =:videoId)" +
            "UNION " +
            "SELECT 'N' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM GOOD_CHART g WHERE g.VIDEO_ID =:videoId)", nativeQuery = true)
    String findMyListYN(@Param("videoId") String videoId);
}