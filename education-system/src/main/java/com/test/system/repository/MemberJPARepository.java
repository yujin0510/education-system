package com.test.system.repository;
import static com.test.system.entity.QMember.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.system.entity.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberJPARepository {

private final JPAQueryFactory jpaQueryFactory;
	
	
	public List<Member> findAll() {

		return jpaQueryFactory.selectFrom(member).fetch();
	}
}
