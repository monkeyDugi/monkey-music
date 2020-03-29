package com.dugi.monkey.config.oauth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 정보를 매번 세션에서 가져오는 코드의 반복을 없애기 위해 생성한 어노테이션 클래스
 */
@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 수 있는 위치 지정
                               // PARAMETER로 지정하였으니 메소드의 파라미터로 선언된 객체에서만 사용 가능.
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 범위로써 어떤 시점까지 영향을 미치는지 결정
public @interface LoginMember {
}
