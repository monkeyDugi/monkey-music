package com.dugi.monkey.web.searchchart;

import com.dugi.monkey.crawling.dto.RequestSearchChartDto;
import com.dugi.monkey.crawling.youtube.searchchart.SearchChartYoutubeSearchAPIProcessing;
import com.dugi.monkey.service.SearchChartService;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchChartController {

    private final SearchChartService searchChartService;

    @GetMapping("api/charts/search/{word}")
    public List<ResponseSearchChartDto> list(@PathVariable("word") String word) {
        return searchChartService.getSearchChartAll(word);
    }
}
