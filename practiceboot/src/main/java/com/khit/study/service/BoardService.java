package com.khit.study.service;

import org.springframework.stereotype.Service;

import com.khit.study.entity.Board;
import com.khit.study.repository.BoardRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardService {

	private BoardRepository boardRepository;
	
	public void save(Board board) {
		boardRepository.save(board);
	}

}
