package com.dugi.monkey.domain.music.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : 이병덕
 * @description : OAuth 회원정보
 * @date : 2020.07.19 22:14:51
 */

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
