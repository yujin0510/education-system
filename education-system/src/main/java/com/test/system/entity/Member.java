package com.test.system.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqMember_genderator")
	@SequenceGenerator(name = "seqMember_genderator", sequenceName = "seqMember", allocationSize = 1)
	@Column(name = "seq", nullable = false)
	private Long seq;
	
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String permission;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private LocalDate birth;
	@Column(nullable = false)
	private String gender;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String status;
	
	public void updatePassword(String password) {
		this.password = password;
		
	}
	
	
}//member
