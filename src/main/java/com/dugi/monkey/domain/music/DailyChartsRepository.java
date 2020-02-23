package com.dugi.monkey.domain.music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyChartsRepository extends JpaRepository<DailyCharts, Long> {

}
