package com.dugi.monkey.web.searchchart;

import com.dugi.monkey.config.oauth.LoginMember;
import com.dugi.monkey.config.oauth.dto.SessionMember;
import com.dugi.monkey.service.SearchChartService;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchChartController {

    private final SearchChartService searchChartService;

    @GetMapping("api/charts/search/{word}")
    public List<ResponseSearchChartDto> findSearchChartAll(@PathVariable("word") String word, @LoginMember SessionMember member) {
        return searchChartService.findSearchChartAll(word, member.getEmail());
    }
}
