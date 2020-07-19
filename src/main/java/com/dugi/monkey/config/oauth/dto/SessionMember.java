package com.dugi.monkey.config.oauth.dto;

import com.dugi.monkey.domain.music.member.Member;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author : 이병덕
 * @description : 인증된 사용자 정보하는 DTO
 * @date : 2020.07.19 21:54:01
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
