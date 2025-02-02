package com.test.system.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User{

	private final MemberDTO memberDTO;
	
	public CustomOAuth2User(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	@Override
	public Map<String, Object> getAttributes() {

		return Map.of();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return memberDTO.getPermission();
			}
		});
		
		return authorities;
	}

	@Override
	public String getName() {

		return memberDTO.getName();
	}
	
	public String getUserName() {
		
		return memberDTO.getUsername();
	}
	
	
}
