package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author : 이병덕
 * @description : 마이 리시트
 * querydsl을 사용하여 Repository를 Custom하여 사용 하기 위해 GoodChartRepositoryCustom를 상속 받음
 * 이렇게 되면 SpringDataJpa가 제공하는 기본 메서드와, Custom한 메서드를 하나의 Repository에서 사용 가능
 * 이렇지 않으면 분리해야 하기 때문에 사용 시 기본 메서드 사용 시는 GoodChartRepository를
 * Custom 메서드 사용 시에는 GoodChartRepositoryCustom를 따로 주입 받아야 하므로 번거롭다.
 * @date : 2020.07.19 22:13:10
 */

public interface GoodChartRepository extends JpaRepository<GoodChart, Long>, GoodChartRepositoryCustom {

    boolean existsByVideoIdAndEmail(String videoId, String email);
    Long deleteByVideoIdAndEmail(String videoId, String email);
}
