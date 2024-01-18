package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	//글쓰기 처리
	public void save(BoardDTO boardDTO) {
		//dto -> entity로 변환
		Board board = Board.toSaveEntity(boardDTO);
		//entity를 db에 저장
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		//db에서 entity list를 가져옴
		List<Board> BoardList = 
				boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		//entity -> dto로 변환
		List<BoardDTO> boardDTOList = new ArrayList<>();
		for(Board board : BoardList) {
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}

	public BoardDTO findById(Long id) {
		//db에서 글 꺼내옴
		Optional<Board> board = boardRepository.findById(id);
		if(board.isPresent()) { //게시글 유무 확인하여 있으면
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board.get());
			return boardDTO;
		}else { //게시글 없으면
			throw new BootBoardException("게시글을 찾을 수 없습니다.");
		}
	}
	//조회수
	@Transactional //어느 하나의 과정 (컨트롤러에 글 상세보기와 조회수 증가 두가지가 있으니 이런 경우에 넣어줘야함)
	public void updateHits(Long id) {
		//조회수 메서드를 boardRepository에 생성
		boardRepository.updateHits(id);
		
	}
	//글삭제
	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}
	//글 수정
	public void update(BoardDTO boardDTO) {
		Board board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board);
	}
	//글 목록 (페이지 포함)
	public Page<BoardDTO> findListAll(Pageable pageable) {
		int page = pageable.getPageNumber() - 1; //db는 현재 페이지보다 1 작음
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findAll(pageable);
		
		log.info("boardList.isFirst()=" + boardList.isFirst());
		log.info("boardList.isLast()=" + boardList.isLast());
		
		//생성자 방식으로 boardDTOList를 가져오기
		Page<BoardDTO> boardDTOList = boardList.map(board ->
				new BoardDTO(board.getId(), board.getBoardTitle(), board.getBoardWriter(),
					board.getBoardContent(), board.getBoardHits(), board.getCreatedDate(),
					board.getUpdatedDate()));
		
		return boardDTOList;
	}
}
