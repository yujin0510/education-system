package com.test.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.system.entity.Board;

public interface ChatRepository extends JpaRepository<Board, Long>{

	
	
}
