package com.dugi.monkey.domain.music.searchchart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSearchChart is a Querydsl query type for SearchChart
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSearchChart extends EntityPathBase<SearchChart> {

    private static final long serialVersionUID = -954216040L;

    public static final QSearchChart searchChart = new QSearchChart("searchChart");

    public final com.dugi.monkey.domain.music.QBaseTimeEntity _super = new com.dugi.monkey.domain.music.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final StringPath videoId = createString("videoId");

    public QSearchChart(String variable) {
        super(SearchChart.class, forVariable(variable));
    }

    public QSearchChart(Path<? extends SearchChart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSearchChart(PathMetadata metadata) {
        super(SearchChart.class, metadata);
    }

}

