package com.dugi.monkey.domain.music.manyToOneExample;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGood is a Querydsl query type for Good
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGood extends EntityPathBase<Good> {

    private static final long serialVersionUID = -725218393L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGood good = new QGood("good");

    public final NumberPath<Long> cnt = createNumber("cnt", Long.class);

    public final StringPath content = createString("content");

    public final QSearch search;

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath title = createString("title");

    public QGood(String variable) {
        this(Good.class, forVariable(variable), INITS);
    }

    public QGood(Path<? extends Good> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGood(PathMetadata metadata, PathInits inits) {
        this(Good.class, metadata, inits);
    }

    public QGood(Class<? extends Good> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.search = inits.isInitialized("search") ? new QSearch(forProperty("search")) : null;
    }

}

