package com.khit.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.board.Entity.Board;
import com.khit.board.Entity.Member;
import com.khit.board.config.SecurityUser;
import com.khit.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public List<Board> findAll() {
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
	}

	public Board findById(Integer id) {
		return boardRepository.findById(id).get();
	}

	public void save(Board board) {
		boardRepository.save(board);
	}

	public void deleteById(Integer id) {
		boardRepository.deleteById(id);
	}
	//글쓰기 페이지
	public Board findById(SecurityUser principal, Integer id) {
		Optional<Board> board = boardRepository.findById(id);
		return board.get();
	}

	public void update(Board board) {
		boardRepository.save(board);
	}

}
