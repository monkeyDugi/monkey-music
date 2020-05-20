package com.dugi.monkey.web.goodchart.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.dugi.monkey.web.goodchart.dto.QResponseGoodChartDto is a Querydsl Projection type for ResponseGoodChartDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QResponseGoodChartDto extends ConstructorExpression<ResponseGoodChartDto> {

    private static final long serialVersionUID = -572469214L;

    public QResponseGoodChartDto(com.querydsl.core.types.Expression<String> tile, com.querydsl.core.types.Expression<String> image, com.querydsl.core.types.Expression<String> videoId) {
        super(ResponseGoodChartDto.class, new Class<?>[]{String.class, String.class, String.class}, tile, image, videoId);
    }

}

