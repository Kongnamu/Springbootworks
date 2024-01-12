package com.khit.study.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khit.study.entity.BoardVO;
import com.khit.study.service.BoardRESTService;

import lombok.AllArgsConstructor;

//return 을 문자열로 반환하는 어노테이션
//@ResponseBody, @ResponseEntity와 비슷함, 이 둘은 메서드에 위치함
@RestController 
@AllArgsConstructor
public class RESTController {
	
	private BoardRESTService boardService;
		
	@GetMapping("/greeting")
	public String sayHello(String name) {
		return "hello" + name; //문자열
	}
	//객체 데이터를 브라우저에 보내줌
	@GetMapping("/board/detail")
	public BoardVO getBoard() {
		BoardVO board = boardService.getBoard();
		return board;
		
		
	}
	@GetMapping("/board/list")
	public List<BoardVO> getBoardList(){
		List<BoardVO> boardList = boardService.getList();
		return boardList;
	}
}
