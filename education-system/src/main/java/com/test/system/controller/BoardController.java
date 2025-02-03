package com.test.system.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.system.entity.Board;
import com.test.system.model.BoardDTO;
import com.test.system.repository.BoardRepository;
import com.test.system.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	private final BoardRepository boardRepository;

	@GetMapping("")
	public String board(Model model) {
		List<BoardDTO> list = boardService.findAll();
	
		model.addAttribute("list", list);
		return "page/board/board";
	}

	
	@GetMapping("/view")
	public String view(Model model, @RequestParam(value = "seq", required = false) Long seq) {
		System.out.println(">>>>"+seq);
		if (seq == null) {
			return "redirect:/board/view";  
		}
		
		Optional<Board> dto = boardRepository.findById(seq);
		BoardDTO dtoDate = boardService.fullDate(dto.get().toDTO());
		model.addAttribute("dto", dtoDate);
		
		return "page/board/view";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/add")
	public String add(Model model) {

		return "page/board/add";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/add/success")
	public String addok(Model model, BoardDTO dto) {

		boardService.boardSave(dto);

		return "redirect:/board";
	}

	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/edit")
	public String edit(Model model) {

		return "page/board/edit";
	}

	//@PreAuthorize("isAuthenticated()")
	@GetMapping("/del")
	public String del(Model model) {

		return "page/board/del";
	}
}
