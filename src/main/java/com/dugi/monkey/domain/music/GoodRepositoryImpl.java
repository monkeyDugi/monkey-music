package com.dugi.monkey.domain.music;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.dugi.monkey.domain.music.QGood.good;

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
