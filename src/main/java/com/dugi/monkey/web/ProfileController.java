package com.dugi.monkey.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author : 이병덕
 * @description : 운영 서버의 포트가 real1, real2 중 무엇인지 판단하기 위한 컨트롤러
 * 운영서버에서 무중단 배포를 위해 포트를 real1 : 8081, real2 : 8082를 사용 중
 * @date : 2020.07.19 22:56:14
 */

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment environment;

    @GetMapping("/profile")
    public String profile() {
        // 현재 실행 중인 ActiveProfile을 모두 가져온다.
        // 즉, real, oauth, real-db등이 활성화 되어 있다면(active) 3개가 모두 담겨 있다.
        // 여기서 real, real1, real2는 모두 배포에 사용될 profile이라 이 중 하나라도 있으면 그 값을 반환
        // real1은 무중단 배포 이전에 사용 했던거로 그냥 남겨 둠.
        // 무중단 배포에서는 real, real2를 사용
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }
}
