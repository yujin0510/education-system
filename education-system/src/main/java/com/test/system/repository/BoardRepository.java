package com.test.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.system.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	
	
	
}