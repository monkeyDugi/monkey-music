package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.goodchart.dto.ResponseGoodChartDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import static com.dugi.monkey.domain.music.goodchart.QGoodChart.goodChart;
import static com.dugi.monkey.domain.music.searchchart.QSearchChart.searchChart;

@RequiredArgsConstructor
public class GoodChartRepositoryImpl implements GoodChartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Transactional // update, delete는 @Transactional이 있어야 한다.
    @Override
    public Long deleteByGoodVideoId(RequestGoodChartDto requestGoodChartDto) {
        return queryFactory
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
    public Page<ResponseGoodChartDto> findByEmailGoodChart(String email, Pageable pageable) {
        QueryResults<ResponseGoodChartDto> result =
                queryFactory.select(
                                    Projections.constructor(
                                    ResponseGoodChartDto.class,
                                    searchChart.title,
                                    searchChart.image,
                                    goodChart.videoId))
                .from(goodChart)
                .leftJoin(searchChart)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .on(goodChart.videoId.eq(searchChart.videoId))
                .where(goodChart.email.eq(email))
                .orderBy(goodChart.id.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

}
