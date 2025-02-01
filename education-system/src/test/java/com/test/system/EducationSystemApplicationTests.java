package com.test.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.test.system.entity.Member;
import com.test.system.repository.MemberRepository;

import lombok.Builder;

@SpringBootTest
class EducationSystemApplicationTests {
/*
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void updataMemberPw() {
		
		Member member = memberRepository.findByUsername("hong1234");
		member.updatePassword(passwordEncoder.encode("hong1111"));
		System.out.println(member);

		
		memberRepository.save(member);
		
		
		//boolean exists = memberRepository.existsByUsername("hong1234");
		//System.out.println("Exists: " + exists);
	}
*/
}
