package com.test.system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClassName is a Querydsl query type for ClassName
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassName extends EntityPathBase<ClassName> {

    private static final long serialVersionUID = 202370440L;

    public static final QClassName className1 = new QClassName("className1");

    public final StringPath className = createString("className");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QClassName(String variable) {
        super(ClassName.class, forVariable(variable));
    }

    public QClassName(Path<? extends ClassName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClassName(PathMetadata metadata) {
        super(ClassName.class, metadata);
    }

}

