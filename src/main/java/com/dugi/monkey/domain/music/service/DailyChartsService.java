package com.dugi.monkey.domain.music.service;

import com.dugi.monkey.crowling.MelonYoutubeCombination;
import com.dugi.monkey.crowling.RequestDailyChartsDto;
import com.dugi.monkey.domain.music.DailyChartsRepository;
import com.dugi.monkey.web.dto.ResponseDailyChartsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DailyChartsService {

    private final DailyChartsRepository dailyChartsRepository;
    private final MelonYoutubeCombination melonYoutubeCombination;

    @Transactional(readOnly = true)
    public List<ResponseDailyChartsDto> findAll() {
        return dailyChartsRepository.findAll()
                                    .stream() // 1. 결과로 넘오온 DailyChars의 stream을
                                    .map(ResponseDailyChartsDto::new) // map을 통해 DailyChartsListResponseDto로 변환 -> List 반환
                                                                      // .map(DailyChars의 -> new DailyChartsListResponseDto(DailyChars의))와 같음
                                    .collect(Collectors.toList());
    }

    @Transactional
    public void save() {
        List<RequestDailyChartsDto> requestDailyChartsDto = melonYoutubeCombination.dailyCharts();

        dailyChartsRepository.deleteAll();

        for (RequestDailyChartsDto dailyChartsDto : requestDailyChartsDto) {
            dailyChartsRepository.save(dailyChartsDto.toEntity());
        }
    }
}
