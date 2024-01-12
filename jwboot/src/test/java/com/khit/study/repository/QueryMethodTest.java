package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryMethodTest {
	
	@Autowired
	private BoardRepository boardRepository;
	
	//테스트 데이터 생성(200개)
	/* @BeforeEach
	public void dataCreate() {
		for(int i = 1; i <= 200; i++) {
			Timestamp now = new Timestamp(System.currentTimeMillis());
			Board board = new Board();
			board.setTitle("test name " + i);
			board.setContent("test content " + i);
			board.setWriter("tester");
			board.setCreatedDate(now);
			
			boardRepository.save(board);
		}
	} */ 
	/* @Test
	public void testFindByTitle() {
		//findByTitle() 호출
		List<Board> boardList = 
				boardRepository.findByTitle("test name 10");
		for(Board board : boardList) {
			log.info(board.toString());
		}
	} */
	
	/* @Test
	public void testFindByTitleContaining() {
		//findByTitle() 호출
		List<Board> boardList = 
				boardRepository.findByTitleContaining("10");
		for(Board board : boardList) {
			log.info(board.toString());
		}
	} */
	@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = 
				boardRepository.findByTitleContainingOrContentContaining("10", "17"); //제목 : 10 / 내용 : 17
	boardList.forEach(board -> log.info(board.toString()));
	}
}
