package com.test.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.system.entity.ViewClassName;
import com.test.system.repository.VwClassNameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {

	private final VwClassNameRepository vwClassNameRepository;
	public List<ViewClassName> findAll() {
		
		List<ViewClassName> list = vwClassNameRepository.findAll();
		
		return list;
	}

}
