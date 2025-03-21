package com.test.system.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class MemberDTO {

	private Long seq;
	private String username;
	private String password;
	private String permission;
	private String name;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private LocalDate birth;
	private String gender;
	private String phone;
	private String status;
	
	// 학생의 클래스명
	private String className;
	
	public void setDefaultValue() {
		this.permission = "0";
		this.status = "1";
	}
	
}
