package com.dugi.monkey.web;

import com.dugi.monkey.config.oauth.LoginMember;
import com.dugi.monkey.config.oauth.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class mustacheController {

    // 구글 소셜 로그인 시 리디렉션 후 리턴 url이 "/" 이라서 필요 함.
    @GetMapping("/")
    public String index() {
        return "redirect:/charts/daily";
    }

    @GetMapping("/charts/daily")
    public String dailyChart(Model model, @LoginMember SessionMember member) {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        return "dailyChart";
    }

    @GetMapping("/charts/search/{word}")
    public String searchMusic(Model model, @PathVariable("word") String word) {
        model.addAttribute("word", word);
        return "searchChart";
    }
}
