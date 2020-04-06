package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dugi.monkey.domain.music.goodchart.QGoodChart.goodChart;
import static com.dugi.monkey.domain.music.searchchart.QSearchChart.searchChart;

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

    @Override
    public Long findMyListExists(RequestGoodChartDto requestGoodChartDto) {
        return queryFactory
                .select()
                .from(goodChart)
                .where(goodChart.videoId.eq(requestGoodChartDto.getVideoId())
                .and(goodChart.email.eq(requestGoodChartDto.getEmail())))
                .fetchCount();
    }

    @Override
    public List<Tuple> findUserGoodChart(String email) {
        return queryFactory
                .select(goodChart.videoId,
                        searchChart.image,
                        searchChart.title
                )
                .from(goodChart)
                .leftJoin(searchChart)
                .on(goodChart.videoId.eq(searchChart.videoId))
                .where(goodChart.email.eq(email))
                .fetch();
    }

}
