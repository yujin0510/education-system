package com.test.system.repository;
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

		return null;
		//return jpaQueryFactory.selectFrom(member).fetch();
	}
}
