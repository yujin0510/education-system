package com.test.system.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.system.entity.Board;
import com.test.system.model.BoardDTO;
import com.test.system.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	//private final BoardJPARepository boardJPARepository;
	private final BoardRepository boardRepository;

	public void boardSave(BoardDTO dto) {
		
		Board board = Board.builder()
						.memberSeq(dto.getMemberSeq())
						.title(dto.getTitle())
						.content(dto.getContent())
						.build();	
		
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		List<Board> lists = boardRepository.findAll();
		
	    List<BoardDTO> dtoList = new ArrayList<>();
		for(Board list : lists) {
			BoardDTO dto = list.toDTO();
			date(dto);
			dtoList.add(dto);
		}
		
		System.out.println(dtoList.toString());
		return dtoList;
	}

	public BoardDTO date(BoardDTO dto) {
	    LocalDateTime creationDateTime = dto.getCreationDate();  // BoardDTO에서 creationDate 가져오기
		// 오늘 날짜 구하기
	    LocalDate today = LocalDate.now();
	    
	    if (creationDateTime.toLocalDate().isEqual(today)) {
	        // 오늘 날짜라면 시간만 설정
	        dto.setDate(creationDateTime.toLocalTime().toString());
	    } else {
	        // 오늘 날짜가 아니라면 날짜와 시간을 모두 설정
	        dto.setDate(creationDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	    }

	    return dto;  // 변경된 dto 반환
	}

	
	public BoardDTO fullDate(BoardDTO dto) {
	    LocalDateTime creationDateTime = dto.getCreationDate();  // BoardDTO에서 creationDate 가져오기

	    dto.setDate(creationDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

	    return dto;  // 변경된 dto 반환
	}

}

