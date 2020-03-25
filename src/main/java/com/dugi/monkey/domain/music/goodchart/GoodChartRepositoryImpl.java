package com.dugi.monkey.domain.music.goodchart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.dugi.monkey.domain.music.goodchart.QGoodChart.goodChart;

@RequiredArgsConstructor
public class GoodChartRepositoryImpl implements GoodChartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteBygoodVideoId(String goodVideoId) {
        queryFactory
        .delete(goodChart)
        .where(goodChart.videoId.eq(goodVideoId))
        .execute();
    }
}
