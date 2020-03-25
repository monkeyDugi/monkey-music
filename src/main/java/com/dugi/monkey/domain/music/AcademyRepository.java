package com.dugi.monkey.domain.music;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * querydsl 예제
 */
public interface AcademyRepository extends JpaRepository<Academy, Long>, AcademyRepositoryCustom {

}
