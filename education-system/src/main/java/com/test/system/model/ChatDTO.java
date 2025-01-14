package com.test.system.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatDTO {

	private String seq;
	private String code;
	private String sender; //member 생기면 변경하기
	private String content;
	private String creationDate;
	
}
