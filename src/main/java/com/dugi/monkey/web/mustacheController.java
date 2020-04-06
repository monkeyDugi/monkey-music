package com.dugi.monkey.web;

import com.dugi.monkey.config.oauth.LoginMember;
import com.dugi.monkey.config.oauth.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.boot.model.source.internal.hbm.XmlElementMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sun.rmi.runtime.Log;

@RequiredArgsConstructor
@Controller
public class mustacheController {

    @GetMapping("/")
    public String dailyChart(Model model, @LoginMember SessionMember member) {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        return "dailyChart";
    }

    @GetMapping("/charts/search/{word}")
    public String searchChart(Model model, @PathVariable("word") String word, @LoginMember SessionMember member) {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        model.addAttribute("word", word);

        return "searchChart";
    }

    @GetMapping("/charts/good")
    public String goodChart(Model model, @LoginMember SessionMember member) {
        if(member != null) {
            model.addAttribute("memberName", member.getName());
        }

        return "goodChart";
    }
}
