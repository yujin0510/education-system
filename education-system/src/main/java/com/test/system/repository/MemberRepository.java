package com.test.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.system.entity.Member;
import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByUsername(String username);
	
	Member findByUsername(String username);
	
}
