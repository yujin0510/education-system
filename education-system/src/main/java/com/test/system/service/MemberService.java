package com.test.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.system.entity.Member;
import com.test.system.model.MemberDTO;
import com.test.system.repository.MemberJPARepository;
import com.test.system.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final MemberJPARepository memberJPARepository;
	
	public void signup(MemberDTO dto) {

		Member member = Member.builder().username(dto.getUsername())
				.password(bCryptPasswordEncoder.encode(dto.getPassword())).permission("0").name(dto.getName())
				.birth(dto.getBirth()).gender(dto.getGender()).phone(dto.getPhone()).status("1").build();

		memberRepository.save(member);
	}

	public List<MemberDTO> findAll() {
		List<Member> lists = memberJPARepository.findAll();

		System.out.println(lists.toString());
		List<MemberDTO> dtoList = new ArrayList<>();
		for (Member list : lists) {
			MemberDTO dto = list.toDTO();
			dtoList.add(dto);
		}
		return dtoList;
	}

}
