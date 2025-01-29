package com.test.system.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
	public void setDefaultValue() {
		this.permission = "0";
		this.status = "1";
	}
	
}
