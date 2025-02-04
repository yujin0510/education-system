package com.test.system.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.system.entity.Member;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails{

	private Member member;
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return member.getPermission();
			}
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {

		return member.getPassword();
	}

	@Override
	public String getUsername() {

		return member.getUsername();
	}
	
	
	public Long getSeq() {

		return member.getSeq();
	}
	
}
