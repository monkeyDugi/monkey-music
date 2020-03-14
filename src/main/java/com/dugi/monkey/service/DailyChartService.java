package com.dugi.monkey.service;

import com.dugi.monkey.crawling.dto.RequestDailyChartDto;
import com.dugi.monkey.domain.music.dailychart.DailyChartRepository;
import com.dugi.monkey.web.dailychart.dto.ResponseDailyChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DailyChartService {

    private final DailyChartRepository dailyChartRepository;

    @Transactional(readOnly = true)
    public List<ResponseDailyChartDto> getDailyChartAll() {
        return dailyChartRepository.findAll()
                .stream()                         // 1. 결과로 넘오온 DailyChars의 stream을
                .map(ResponseDailyChartDto::new)  // map을 통해 DailyChartsListResponseDto로 변환 -> List 반환
                                                  // .map(DailyChars의 -> new DailyChartsListResponseDto(DailyChars의))와 같음
                .collect(Collectors.toList());
    }

    @Transactional
    public void addDailyChart(List<RequestDailyChartDto> requestDailyChartDtos) {
        dailyChartRepository.deleteAll();

        for (RequestDailyChartDto dailyChartsDto : requestDailyChartDtos) {
            dailyChartRepository.save(dailyChartsDto.toEntity());
        }
    }
}