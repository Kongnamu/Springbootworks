package com.khit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public void save(BoardDTO boardDTO, MultipartFile boardFile) throws Exception {
		//1. 파일을 서버에 저장하고 
		if(!boardFile.isEmpty()) { //전달된 파일이 있으면
			//저장 경로
			String filePath = "C:\\Springbootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			//중복된 파일명 없도록 설정
			UUID uuid = UUID.randomUUID(); //무작위 아이디 생성
			String fileName = uuid + "_" +  boardFile.getOriginalFilename(); //원본 파일
			//File 클래스 객체 생성
			File savedFile = new File(filePath, fileName); //upload 폴더에 저장될 파일
			boardFile.transferTo(savedFile);
			
			//2. 파일 이름은 db로 저장
			boardDTO.setFilename(fileName);
			boardDTO.setFilepath("/upload/" + fileName); //upload경로를 표기하기 위함
		} 
	
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
	public void update(BoardDTO boardDTO, MultipartFile boardFile) throws Exception {
		//1. 파일을 서버에 저장하고 
		if(!boardFile.isEmpty()) { //전달된 파일이 있으면 
			//저장 경로
			String filePath = "C:\\Springbootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			//중복된 파일명 없도록 설정
			UUID uuid = UUID.randomUUID(); //무작위 아이디 생성
			String fileName = uuid + "_" +  boardFile.getOriginalFilename(); //원본 파일
			//File 클래스 객체 생성
			File savedFile = new File(filePath, fileName); //upload 폴더에 저장될 파일
			boardFile.transferTo(savedFile);
			
			//2. 파일 이름을 db로 저장
			boardDTO.setFilename(fileName);
			boardDTO.setFilepath("/upload/" + fileName); //upload경로를 표기하기 위함
		}else {
			//수정할 파일이 없으면 게시글 번호 경로만 보여준다.
			boardDTO.setFilepath(findById(boardDTO.getId()).getFilepath());
		}
		//save() : 삽입(id가 없고), 수정 (id가 있음)
		//dto -> entity
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
		log.info("boardList.isLast()=" + boardList.getNumber());
		
		//생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board ->
				new BoardDTO(board.getId(), board.getBoardTitle(), board.getBoardWriter(),
					board.getBoardContent(), board.getBoardHits(), board.getFilepath(),
					board.getFilename(), board.getCreatedDate(),
					board.getUpdatedDate()));
		
		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardTitleContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() - 1; //db는 현재 페이지보다 1 작음
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findByBoardTitleContaining(keyword, pageable);
		
		//생성자 방식으로 boardDTOList를 가져오기
		Page<BoardDTO> boardDTOList = boardList.map(board ->
				new BoardDTO(board.getId(), board.getBoardTitle(), board.getBoardWriter(),
						board.getBoardContent(), board.getBoardHits(), board.getFilepath(),
						board.getFilename(), board.getCreatedDate(),
						board.getUpdatedDate()));
		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardContentContaining(String keyword, Pageable pageable) {
		int page = pageable.getPageNumber() - 1; //db는 현재 페이지보다 1 작음
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		
		Page<Board> boardList = boardRepository.findByBoardContentContaining(keyword, pageable);
		
		//생성자 방식으로 boardDTOList를 가져오기
		Page<BoardDTO> boardDTOList = boardList.map(board ->
				new BoardDTO(board.getId(), board.getBoardTitle(), board.getBoardWriter(),
						board.getBoardContent(), board.getBoardHits(), board.getFilepath(),
						board.getFilename(), board.getCreatedDate(),
						board.getUpdatedDate()));
		return boardDTOList;
	}

	
}
