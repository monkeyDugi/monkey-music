package com.dugi.monkey.web;

import com.dugi.monkey.config.oauth.LoginMember;
import com.dugi.monkey.config.oauth.dto.SessionMember;
import com.dugi.monkey.service.GoodChartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author : 이병덕
 * @description : view 컨트롤러
 * @date : 2020.07.19 22:55:32
 */

@Slf4j
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
    @GetMapping("/charts/search")
    public String searchChart(Model model, @RequestParam("word") String word, @LoginMember SessionMember member) throws UnsupportedEncodingException {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        log.info("검색 키워드 word : " + word);

        String encode = URLEncoder.encode(word, "UTF-8").replace("+", "%20");
        log.info("검색 키워드 word encoding : " + encode);

        model.addAttribute("word", encode);

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
