package com.dugi.monkey.domain.music.manyToOneExample;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSearch is a Querydsl query type for Search
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSearch extends EntityPathBase<Search> {

    private static final long serialVersionUID = -816259566L;

    public static final QSearch search = new QSearch("search");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public QSearch(String variable) {
        super(Search.class, forVariable(variable));
    }

    public QSearch(Path<? extends Search> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSearch(PathMetadata metadata) {
        super(Search.class, metadata);
    }

}

