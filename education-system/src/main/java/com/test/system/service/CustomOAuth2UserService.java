package com.test.system.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.test.system.entity.Member;
import com.test.system.model.GoogleResponse;
import com.test.system.model.MemberDTO;
import com.test.system.model.OAuth2Response;
import com.test.system.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("구글로부터 받아온 개인 정보: " + oAuth2User);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		OAuth2Response oAuth2Response = null;
		
		if (registrationId.equals("google")) {
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes()); 
		} else {
			return null;
		}
		
		//유저 식별자 중복 문제
		/*
		String username = oAuth2Response.getProviderId() + " " + oAuth2Response.getProviderId();
		
		System.out.println("우리 사이트에서 사용할 ID: " + username);
		
		MemberDTO memberDTO = new MemberDTO();
		
		memberDTO.setUsername(username);
		memberDTO.setName(oAuth2Response.getName());
		memberDTO.setPermission("ROLE_MEMBER");
		
		Member member = memberRepository.findByUsername(username);
		*/
		
		return super.loadUser(userRequest);
	}
	
}
