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

@RequiredArgsConstructor
@RestController
public class GoodChartController {

    private final GoodChartService goodChartService;

    @PostMapping("api/charts/good/{goodVideoId}")
    public void addGoodChart(@PathVariable("goodVideoId") String goodVideoId, @LoginMember SessionMember member) {
        goodChartService.addGoodChart(RequestGoodChartDto.builder()
                                                                .videoId(goodVideoId)
                                                                .email(member.getEmail())
                                                                .build());
    }

    @DeleteMapping("api/charts/good/{goodVideoId}")
    public Long deleteGoodChart(@PathVariable("goodVideoId") String goodVideoId, @LoginMember SessionMember member) {
        return goodChartService.deleteGoodChart(RequestGoodChartDto.builder()
                      .videoId(goodVideoId)
                      .email(member.getEmail())
                      .build());
    }

    @GetMapping("api/charts/good")
    public Page<ResponseGoodChartDto> getUserGoodChart(@LoginMember SessionMember member, @PageableDefault(size = 5) Pageable pageable) {
        return goodChartService.getUserGoodChart(member.getEmail(), pageable);
    }
}
