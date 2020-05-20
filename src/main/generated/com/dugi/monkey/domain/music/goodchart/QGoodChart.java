package com.dugi.monkey.domain.music.goodchart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodChart is a Querydsl query type for GoodChart
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodChart extends EntityPathBase<GoodChart> {

    private static final long serialVersionUID = 318569198L;

    public static final QGoodChart goodChart = new QGoodChart("goodChart");

    public final com.dugi.monkey.domain.music.QBaseTimeEntity _super = new com.dugi.monkey.domain.music.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath videoId = createString("videoId");

    public QGoodChart(String variable) {
        super(GoodChart.class, forVariable(variable));
    }

    public QGoodChart(Path<? extends GoodChart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodChart(PathMetadata metadata) {
        super(GoodChart.class, metadata);
    }

}

