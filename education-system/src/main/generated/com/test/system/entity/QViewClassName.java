package com.test.system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QViewClassName is a Querydsl query type for ViewClassName
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QViewClassName extends EntityPathBase<ViewClassName> {

    private static final long serialVersionUID = -1411333661L;

    public static final QViewClassName viewClassName = new QViewClassName("viewClassName");

    public final StringPath className = createString("className");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath username = createString("username");

    public QViewClassName(String variable) {
        super(ViewClassName.class, forVariable(variable));
    }

    public QViewClassName(Path<? extends ViewClassName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QViewClassName(PathMetadata metadata) {
        super(ViewClassName.class, metadata);
    }

}

