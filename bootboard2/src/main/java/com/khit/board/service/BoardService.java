package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO) {
		//dto를 엔티티로 변환
		Board board = Board.toSaveEntity(boardDTO);
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		List<Board> BoardList = boardRepository.findAll();
		List<BoardDTO> boardDTOList = new ArrayList<>(); 
		for(Board board : BoardList) {
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}

	public BoardDTO findById(Long id) {
		Optional<Board> board = boardRepository.findById(id);
		if(board.isPresent()) {
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board.get());
			return boardDTO;
		}
		return null;
	}
	//조회수
	@Transactional
	public void updateHits(Long id) {
		boardRepository.updateHits(id);
	}
	//삭제
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}

	public void update(BoardDTO boardDTO) {
		Board board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board);
	}
}
