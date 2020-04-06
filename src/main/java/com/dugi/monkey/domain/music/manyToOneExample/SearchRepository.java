package com.dugi.monkey.domain.music.manyToOneExample;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<Search, String> {
}
