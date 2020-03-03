package com.dugi.monkey.service;

import com.dugi.monkey.crawling.RequestDailyChartDto;
import com.dugi.monkey.domain.music.DailyChartRepository;
import com.dugi.monkey.web.dto.ResponseDailyChartDto;
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
    public List<ResponseDailyChartDto> getDailyChart() {
        return dailyChartRepository.findAll()
                .stream()                         // 1. 결과로 넘오온 DailyChars의 stream을
                .map(ResponseDailyChartDto::new)  // map을 통해 DailyChartsListResponseDto로 변환 -> List 반환
                                                  // .map(DailyChars의 -> new DailyChartsListResponseDto(DailyChars의))와 같음
                .collect(Collectors.toList());
    }

    @Transactional
    public void addDailyChart(List<RequestDailyChartDto> requestDailyChartDtoList) {
        dailyChartRepository.deleteAll();

        for (RequestDailyChartDto dailyChartsDto : requestDailyChartDtoList) {
            dailyChartRepository.save(dailyChartsDto.toEntity());
        }
    }
}