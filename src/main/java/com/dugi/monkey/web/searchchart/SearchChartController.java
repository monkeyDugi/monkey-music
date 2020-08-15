package com.dugi.monkey.web.searchchart;

import com.dugi.monkey.config.oauth.LoginMember;
import com.dugi.monkey.config.oauth.dto.SessionMember;
import com.dugi.monkey.service.SearchChartService;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : 이병덕
 * @description : 검색차트 조회
 * @date : 2020.07.19 22:54:18
 */

@RequiredArgsConstructor
@RestController
public class SearchChartController {

    private final SearchChartService searchChartService;

    // 검색차트 조회
    @GetMapping("api/charts/search")
    public List<ResponseSearchChartDto> findSearchChartAll(@RequestParam("word") String word, @LoginMember SessionMember member) {
        // 테스트 코드 작성 시에 세션이 없으므로 @LoginMember SessionMember member로 인한 NPE 발생
        // 테스트 코드 작성 시 "test@test.com"로 fix하여 테스트
        // 실제 구현 시에는 member가 null 일 수 없으므로 항상 member.getEmail()을 사용
        // 구현코드에 테스트 관련 코드가 들어가는 건 좋지 않으므로, 오키에 올린 글 참고하여, 테스트 변경 하기.
        String email = "test@test.com";
        if(member != null) email = member.getEmail();

        return searchChartService.findSearchChartAll(word, email);
    }
}
