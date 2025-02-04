package com.test.system.entity;

import com.test.system.model.ClassListDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "classList")
public class ClassList {

	@Id
	private Long seq;

	@Column(name = "MEMBER_SEQ")
	private String memberSeq;
	
	@Column(name = "class_seq")
	private String classSeq;

	public ClassListDTO toDTO() {

		return ClassListDTO.builder()
				.seq(this.getSeq())
				.memberSeq(this.getMemberSeq())
				.classSeq(this.getClassSeq())
				.build();
	}
	
	
	
}
