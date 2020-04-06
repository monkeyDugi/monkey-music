package com.dugi.monkey.domain.music.manyToOneExample;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.dugi.monkey.domain.music.manyToOneExample.QGood.good;

@RequiredArgsConstructor
public class GoodRepositoryImpl implements GoodRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long findExists(String c) {
        return jpaQueryFactory
                .select()
                .from(good)
                .where(good.title.eq(c))
                .fetchCount();

    }
}
