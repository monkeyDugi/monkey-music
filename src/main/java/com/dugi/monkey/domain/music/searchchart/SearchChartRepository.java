package com.dugi.monkey.domain.music.searchchart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface SearchChartRepository extends JpaRepository<SearchChart, Long>, SearchChartRepositoryCustom {
}
