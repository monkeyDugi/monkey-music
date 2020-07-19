package com.dugi.monkey.service;

import com.dugi.monkey.crawling.youtube.dto.ResponseMelonYoutubeCombinationDto;
import com.dugi.monkey.domain.music.dailychart.DailyChartRepository;

import com.dugi.monkey.web.dailychart.dto.ResponseDailyChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : 이병덕
 * @description : 일간차트 검색 및 등록
 * @date : 2020.07.19 22:21:07
 */

@RequiredArgsConstructor
@Service
public class DailyChartService {

    private final DailyChartRepository dailyChartRepository;

    // 스케줄러에 의해 일간차트 DB에 등록된 일간차트 전체 검색
    @Transactional(readOnly = true)
    public List<ResponseDailyChartDto> findDailyChartAll() {
        return dailyChartRepository.findAll()
                .stream()                         // 1. 결과로 넘오온 DailyChars의 stream을
                .map(ResponseDailyChartDto::new)  // map을 통해 DailyChartsListResponseDto로 변환 -> List 반환
                                                  // .map(DailyChars의 -> new DailyChartsListResponseDto(DailyChars의))와 같음
                .collect(Collectors.toList());
    }

    // 스케줄러에 의해 가져온 일간차트 데이터를 일간차트 DB에 저장
    @Transactional
    public void addDailyChart(List<ResponseMelonYoutubeCombinationDto> requestDailyChartDtos) {
        dailyChartRepository.deleteAll();

        for (ResponseMelonYoutubeCombinationDto dailyChartsDto : requestDailyChartDtos) {
            dailyChartRepository.save(dailyChartsDto.toEntity());
        }
    }
}