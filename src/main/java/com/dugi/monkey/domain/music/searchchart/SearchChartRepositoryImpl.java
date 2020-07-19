package com.dugi.monkey.domain.music.searchchart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.dugi.monkey.domain.music.searchchart.QSearchChart.searchChart;

/**
 * @author : 이병덕
 * @description : 검색차트 qeuryDsl
 * @date : 2020.07.19 22:16:11
 */

@RequiredArgsConstructor
public class SearchChartRepositoryImpl implements SearchChartRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long findByExistsVideoId(String videoId) {
        return queryFactory
                .select()
                .from(searchChart)
                .where(searchChart.videoId.eq(videoId))
                .fetchCount();
    }
}
