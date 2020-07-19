package com.dugi.monkey.domain.music.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 스프링 시큐리티에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 함
 */

/**
 * @author : 이병덕
 * @description : 스프링 시큐리티에서의 권한 코드
 * 시큐리티에서 권한은 반드시 ROLE_ 이 있어야 함
 * @date : 2020.07.19 22:15:07
 */

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
