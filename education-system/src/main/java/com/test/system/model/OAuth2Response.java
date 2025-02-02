package com.test.system.model;

public interface OAuth2Response {

	//제공자 naver, google
	String getProvider();
	
	//식별자, 제공자에게 발급하는 아이디
	String getProviderId();
	
	//이메일
	String getEmail();
	
	//사용자명
	String getName();
	
}
