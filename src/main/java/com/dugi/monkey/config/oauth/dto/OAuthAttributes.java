package com.dugi.monkey.config.oauth.dto;

import com.dugi.monkey.domain.music.member.Member;
import com.dugi.monkey.domain.music.member.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * DTO 역할
 */
@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    // OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야 함.
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때 인데
    // 이 때 기본 권한을 USER로 주기 위해서 role 빌더값에 USER 사용
    // 나중에 종류 추가되면 더 추가할 거임. GUEST 같은.
    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();
    }
}
