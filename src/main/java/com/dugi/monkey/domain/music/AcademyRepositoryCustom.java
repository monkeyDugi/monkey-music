package com.dugi.monkey.domain.music;

import java.util.List;

/**
 * querydsl 예제
 */
public interface AcademyRepositoryCustom {

    List<Academy> findNyName(String name);
}
