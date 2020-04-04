package com.dugi.monkey.domain.music.member;

import com.dugi.monkey.domain.music.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING) // JPA로 DB 저장 시 Enum 값을 어떤 형태로 저장할지 결정
                                 // 기본값 int
                                 // 숫자로 저장하면 DB로 확인 시 무슨 코드를 의미하는지 알 수가 없기 때문에 문자열로 저장하는 것을 택함.
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Member update(String name) {
        this.name = name;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
