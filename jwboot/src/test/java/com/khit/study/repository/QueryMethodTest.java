package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
	
	/* @Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = 
				boardRepository.findByTitleContainingOrContentContaining("10", "17"); //제목 : 10 / 내용 : 17
	boardList.forEach(board -> log.info(board.toString()));
	} */
	
	/* @Test
	public void testFindByTitleContainingOrderByIdDesc() {
		List<Board> boardList = 
				boardRepository.findByTitleContainingOrderByIdDesc("10");
		for(Board board : boardList) {
			log.info(board.toString());
		}
	} */
	
	/* @Test
	public void testFindByTitleContainingPaging() {
		//0 : 1페이지
		//Pageable paging = PageRequest.of(1, 5);
		Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
		log.info("page: " + paging.getPageNumber());
		log.info("page: " + paging.getPageSize());
		
		List<Board> boardList = 
				boardRepository.findByTitleContaining("name", paging);
		
		boardList.forEach(board -> log.info(board.toString()));
	} */
	@Test
	public void testPaging() {
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
		
		Page<Board> pageInfo = 
				boardRepository.findByTitleContaining("name", paging);
		
		//number(페이지 번호), totalPages, totalElements, content
		log.info("페이지 번호" + pageInfo.getNumber());
		log.info("페이지당 게시글 수" + pageInfo.getSize());
		log.info("게시글 총 개수" + pageInfo.getTotalElements()); // 총 개수
		log.info("게시글 총 페이지수" + pageInfo.getTotalPages());
		
		List<Board> boardList = pageInfo.getContent();
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}
}
