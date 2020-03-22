package com.dugi.monkey.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mustacheController {

    @GetMapping("/charts/daily")
    public String dailyChart(Model model) {
        return "dailyChart";
    }

//    @GetMapping("/charts/search")
    @GetMapping("/charts/search/{word}")
    public String searchMusic(Model model, @PathVariable("word") String word) {
        model.addAttribute("word", word);
        return "searchChart";
    }
}
