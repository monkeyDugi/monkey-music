package com.dugi.monkey.config.oauth;

import com.dugi.monkey.config.oauth.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * @author : 이병덕
 * @description
 * HandlerMethodArgumentResolver
 * - 조건에 맞는 경우 메서드가 있다면 HandlerMethodArgumentResolver의 구현체가 지정한 값으로
 *   해당 메서드의 파라미터로 넘길 수 있다.
 * @date : 2020.07.19 21:56:08
 */
@RequiredArgsConstructor
@Component
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    // 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단
    // 여기서는 파리미터에 @LoginMember 어노테이션이 붙어 있고, 파라미터 클래스 타입이 SessionMember.class인 경우 true 반환
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginMemberAnnotation = parameter.getParameterAnnotation(LoginMember.class) != null;
        boolean isMemberClass = SessionMember.class
                                              .equals(parameter.getParameterType());
        return isLoginMemberAnnotation && isMemberClass;
    }

    // 파리미터에 전달한 객체를 생성
    // 여기서는 세션에서 객체를 가져온다.
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("member");
    }
}
