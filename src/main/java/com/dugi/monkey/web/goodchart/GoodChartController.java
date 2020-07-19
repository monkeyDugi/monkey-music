package com.dugi.monkey.web.goodchart;

import com.dugi.monkey.config.oauth.LoginMember;
import com.dugi.monkey.config.oauth.dto.SessionMember;
import com.dugi.monkey.service.GoodChartService;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.goodchart.dto.ResponseGoodChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : 이병덕
 * @description : 마이리스트 컨트롤러
 * @date : 2020.07.19 22:53:12
 */

@RequiredArgsConstructor
@RestController
public class GoodChartController {

    private final GoodChartService goodChartService;

    @PostMapping("api/charts/good/{goodVideoId}")
    public void addGoodChart(@PathVariable("goodVideoId") String goodVideoId, @LoginMember SessionMember member) {
        String email = "test@test.com";

        if(member != null) email = member.getEmail();

        goodChartService.addGoodChart(RequestGoodChartDto.builder()
                                                                .videoId(goodVideoId)
                                                                .email(email)
                                                                .build());
    }

    @DeleteMapping("api/charts/good/{goodVideoId}")
    public Long deleteByGoodVideoId(@PathVariable("goodVideoId") String goodVideoId, @LoginMember SessionMember member) {
        String email = "test@test.com";

        if(member != null) email = member.getEmail();

        return goodChartService.deleteByGoodVideoId(RequestGoodChartDto.builder()
                      .videoId(goodVideoId)
                      .email(email)
                      .build());
    }

    @GetMapping("api/charts/good")
    public Page<ResponseGoodChartDto> findByEmailGoodChart(@LoginMember SessionMember member, @PageableDefault(size = 5) Pageable pageable) {
        String email = "test@test.com";

        if(member != null) email = member.getEmail();

        return goodChartService.findByEmailGoodChart(email, pageable);
    }
}
