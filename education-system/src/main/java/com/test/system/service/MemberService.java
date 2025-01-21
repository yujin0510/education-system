package com.test.system.service;

import org.springframework.stereotype.Service;

import com.test.system.entity.Member;
import com.test.system.model.MemberDTO;
import com.test.system.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;

	public void signup(MemberDTO dto) {
		
		Member member = Member.builder()
								.userid(dto.getUserid())
								.password(dto.getPassword())
								.permission("0")
								.name(dto.getName())
								.birth(dto.getBirth())
								.gender(dto.getGender())
								.phone(dto.getPhone())
								.status("1")
								.build();
		
		memberRepository.save(member);
	}
	
}
