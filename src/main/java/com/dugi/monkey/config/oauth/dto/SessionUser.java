package com.dugi.monkey.config.oauth.dto;

import com.dugi.monkey.domain.music.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * 인증된 사용자 정보만 저장하는 dto
 */
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
