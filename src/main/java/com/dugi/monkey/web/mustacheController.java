package com.dugi.monkey.web;

import com.dugi.monkey.config.oauth.LoginMember;
import com.dugi.monkey.config.oauth.dto.SessionMember;
import com.dugi.monkey.service.GoodChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : 이병덕
 * @description : view 컨트롤러
 * @date : 2020.07.19 22:55:32
 */

@RequiredArgsConstructor
@Controller
public class mustacheController {

    private final GoodChartService goodChartService;

    // 일간차트
    @GetMapping("/")
    public String dailyChart(Model model, @LoginMember SessionMember member) {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        return "dailyChart";
    }

    // 검색차트
    @GetMapping("/charts/search/{word}")
    public String searchChart(Model model, @PathVariable("word") String word, @LoginMember SessionMember member) {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        model.addAttribute("word", word);

        return "searchChart";
    }

    // 마이리스트
    @GetMapping("/charts/good")
    public String goodChart(Model model, @LoginMember SessionMember member) {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        return "goodChart";
    }
}
