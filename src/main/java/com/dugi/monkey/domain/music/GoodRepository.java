package com.dugi.monkey.domain.music;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Long>, GoodRepositoryCustom{
}
