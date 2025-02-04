package com.test.system.entity;

import java.time.LocalDateTime;

import com.test.system.model.BoardDTO;

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
@Table(name = "board")
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqBoard_genderator")
	@SequenceGenerator(name = "seqBoard_genderator", sequenceName = "seqBoard", allocationSize = 1)
	private Long seq;

	@Column(name = "MEMBER_SEQ")
	private String memberSeq;
	private String title;
	private String content;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	private String count;

	@PrePersist
	public void prePersist() {
		if (this.creationDate == null) {
			this.creationDate = LocalDateTime.now();
			// 삽입되기 전에 creationDate 값을 자동으로 설정
		}

		if (this.modificationDate == null) {
			this.modificationDate = LocalDateTime.now();
			// 삽입되기 전에 creationDate 값을 자동으로 설정
		}

		if (this.count == null) {
			this.count = "0";
			// 삽입되기 전에 count 값을 자동으로 설정
		}
	}

	public BoardDTO toDTO() {

		return BoardDTO.builder()
				.seq(this.getSeq())
				.memberSeq(this.getMemberSeq())
				.title(this.getTitle())
				.content(this.getContent())
				.creationDate(this.getCreationDate())
				.modificationDate(this.getModificationDate())
				.count(this.getCount())
				.build();
	}

}
