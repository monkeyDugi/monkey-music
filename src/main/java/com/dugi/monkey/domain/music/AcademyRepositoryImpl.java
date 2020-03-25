package com.dugi.monkey.domain.music;

import com.querydsl.jpa.impl.JPAQueryFactory;
import static com.dugi.monkey.domain.music.QAcademy.academy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * querydsl 예제
 */
@RequiredArgsConstructor
public class AcademyRepositoryImpl implements AcademyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Academy> findNyName(String name) {
        return queryFactory
                .selectFrom(academy)
                .where(academy.name.eq(name))
                .fetch();
    }
}
