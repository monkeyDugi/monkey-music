package com.dugi.monkey.service;

import com.dugi.monkey.domain.music.goodchart.GoodChartRepository;
import com.dugi.monkey.domain.music.goodchart.QGoodChart;
import com.dugi.monkey.domain.music.searchchart.QSearchChart;
import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import com.dugi.monkey.web.goodchart.dto.ResponseGoodChartDto;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.dugi.monkey.domain.music.goodchart.QGoodChart.goodChart;
import static com.dugi.monkey.domain.music.searchchart.QSearchChart.searchChart;

@RequiredArgsConstructor
@Service
public class GoodChartService {

    private final GoodChartRepository goodChartRepository;

    @Transactional
    public void addGoodChart(RequestGoodChartDto requestGoodChartDto) {
        goodChartRepository.save(requestGoodChartDto.toEntity());
    }

    @Transactional
    public void deleteGoodChart(RequestGoodChartDto requestGoodChartDto) {
        goodChartRepository.deleteByGoodVideoId(requestGoodChartDto);
    }

    @Transactional
    public List<ResponseGoodChartDto> getUserGoodChart(String email) {
        List<Tuple> tuples = goodChartRepository.findUserGoodChart(email);
        List<ResponseGoodChartDto> responseGoodChartDtos = new ArrayList<>();
        ResponseGoodChartDto responseGoodChartDto;

        for(Tuple tuple : tuples) {
            responseGoodChartDto = ResponseGoodChartDto.builder()
                                                        .tile(tuple.get(searchChart.title))
                                                        .image(tuple.get(searchChart.image))
                                                        .videoId(tuple.get(goodChart.videoId))
                                                        .build();

            responseGoodChartDtos.add(responseGoodChartDto);
        }

        return responseGoodChartDtos;
    }
}
