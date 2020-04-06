package com.dugi.monkey.domain.music.manyToOneExample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Long>, GoodRepositoryCustom {
}
