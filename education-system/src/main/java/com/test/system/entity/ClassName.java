package com.test.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "class")
public class ClassName {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqClass_genderator")
	private Long seq;

	@Column(name = "MEMBER_SEQ")
	private String className;


	

	
	
}
