package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	//게시글 생성
	@Test
	public void insertBoard() {
		 /* Board board = new Board();
		board.setTitle("가입 인사");
		board.setWriter("천재신입");
		board.setContent("난 짱짱맨이다!");
		board.setCreatedDate(new Timestamp(System.currentTimeMillis())); */
		
		/* Board board = Board.builder()
				.title("가입 인사3")
				.writer("천재신입3")
				.content("코딩킹")
				.createdDate(new Timestamp(System.currentTimeMillis()))
				.build(); */
		
		//db에 저장
		//boardRepository.save(board);
	}
	@Test
	public void getBoardList() {
		//db에서 게시글 목록 가져오기
		List<Board> boardList = boardRepository.findAll();
		//boardList출력
		/* for(Board board: boardList)
			log.info(board.toString()); */
		
		//람다식
		boardList.forEach(board -> log.info(board.toString()));
	}
	//1건 상세보기
	@Test
	public void getBoard() {
		//findById()와 get()사용
		Board board = boardRepository.findById(2).get();
		log.info(board.toString());
	}
	//수정하기
	@Test
	public void updateBoard() {
		//수정하려는 글 가져오기(findById), 수정 처리(save)
		Board board = boardRepository.findById(1).get();
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		//저장(수정)
		boardRepository.save(board);
	}
	//삭제하기
	@Test
	public void deleteBoard() {
		//3번 게시글 삭제
		boardRepository.deleteById(3);
		
	}
}
