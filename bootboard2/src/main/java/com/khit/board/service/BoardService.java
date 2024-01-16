package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.repository.BoardRepository;

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


	
}
