package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.dugi.monkey.domain.music.goodchart.QGoodChart.goodChart;

@RequiredArgsConstructor
public class GoodChartRepositoryImpl implements GoodChartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteByGoodVideoId(RequestGoodChartDto requestGoodChartDto) {
        queryFactory
        .delete(goodChart)
        .where(goodChart.videoId.eq(requestGoodChartDto.getVideoId())
        .and(goodChart.email.eq(requestGoodChartDto.getEmail())))
        .execute();
    }
}
