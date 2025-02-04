package com.test.system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClassList is a Querydsl query type for ClassList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClassList extends EntityPathBase<ClassList> {

    private static final long serialVersionUID = 202318747L;

    public static final QClassList classList = new QClassList("classList");

    public final StringPath classSeq = createString("classSeq");

    public final StringPath memberSeq = createString("memberSeq");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QClassList(String variable) {
        super(ClassList.class, forVariable(variable));
    }

    public QClassList(Path<? extends ClassList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClassList(PathMetadata metadata) {
        super(ClassList.class, metadata);
    }

}

