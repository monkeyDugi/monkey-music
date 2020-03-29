package com.dugi.monkey.config.oauth.dto;

import com.dugi.monkey.domain.music.member.Member;
import lombok.Getter;

import java.io.Serializable;

/**
 * 인증된 사용자 정보만 저장하는 dto
 */
@Getter
public class SessionMember implements Serializable {

    private String name;
    private String email;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
