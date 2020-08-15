package com.dugi.monkey.service;

import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.goodchart.dto.ResponseGoodChartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : 이병덕
 * @description : 마이리스트 검색, 등록, 삭제
 * @date : 2020.07.19 22:22:54
 */

@RequiredArgsConstructor
@Service
public class GoodChartService {

    private final GoodChartRepository goodChartRepository;

    // 마이리스트 등록
    @Transactional
    public void addGoodChart(RequestGoodChartDto requestGoodChartDto) {
        goodChartRepository.save(requestGoodChartDto.toEntity());
    }

    // 마이리스트 제거
    @Transactional
    public Long deleteByGoodVideoId(String videoId, String email) {
        return goodChartRepository.deleteByVideoIdAndEmail(videoId, email);
    }

    // 마이리스트에서 이메일별로 검색
    @Transactional
    public Page<ResponseGoodChartDto> findByEmailGoodChart(String email, Pageable pageable) {
        return goodChartRepository.findByEmailGoodChart(email, pageable);
    }
}
