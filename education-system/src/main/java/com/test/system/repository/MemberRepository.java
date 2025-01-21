package com.test.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.system.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	//회원 아이디 찾기
	Member findByUserid(String userid);
	
}
