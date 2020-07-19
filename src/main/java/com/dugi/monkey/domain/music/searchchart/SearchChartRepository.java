package com.dugi.monkey.domain.music.searchchart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author : 이병덕
 * @description : 검색차트
 * @date : 2020.07.19 22:16:01
 */

public interface SearchChartRepository extends JpaRepository<SearchChart, Long>, SearchChartRepositoryCustom {
}
