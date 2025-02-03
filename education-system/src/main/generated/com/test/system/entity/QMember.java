package com.test.system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1497545557L;

    public static final QMember member = new QMember("member1");

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    public final StringPath gender = createString("gender");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath permission = createString("permission");

    public final StringPath phone = createString("phone");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public final StringPath status = createString("status");

    public final StringPath username = createString("username");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

