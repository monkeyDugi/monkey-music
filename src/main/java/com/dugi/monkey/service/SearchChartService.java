package com.dugi.monkey.service;

import com.dugi.monkey.crawling.dto.RequestSearchChartDto;
import com.dugi.monkey.domain.music.searchchart.SearchChartRepository;
import com.dugi.monkey.web.searchchart.dto.ResponseSearchChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchChartService {

    private final SearchChartRepository searchChartRepository;

    @Transactional(readOnly = true)
    public List<ResponseSearchChartDto> getSearchChartAll(List<RequestSearchChartDto> requestSearchChartDtos) {
        return null;
    }
}
