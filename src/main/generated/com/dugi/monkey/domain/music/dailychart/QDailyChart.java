package com.dugi.monkey.domain.music.dailychart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDailyChart is a Querydsl query type for DailyChart
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDailyChart extends EntityPathBase<DailyChart> {

    private static final long serialVersionUID = -262453912L;

    public static final QDailyChart dailyChart = new QDailyChart("dailyChart");

    public final com.dugi.monkey.domain.music.QBaseTimeEntity _super = new com.dugi.monkey.domain.music.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath rank = createString("rank");

    public final StringPath singer = createString("singer");

    public final StringPath title = createString("title");

    public final StringPath videoId = createString("videoId");

    public QDailyChart(String variable) {
        super(DailyChart.class, forVariable(variable));
    }

    public QDailyChart(Path<? extends DailyChart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDailyChart(PathMetadata metadata) {
        super(DailyChart.class, metadata);
    }

}

