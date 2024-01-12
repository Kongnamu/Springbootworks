package com.khit.study.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.khit.study.entity.BoardVO;

@Controller
public class BoardRESTService {
	
	//상세보기
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setId(1);
		board.setTitle("제목");
		board.setWriter("작성자");
		board.setContent("내용임");
		board.setCreatedDate(new Date());
		
		return board;
	}
	//목록 보기
	public List<BoardVO> getList() {
		List<BoardVO> boardList = new ArrayList<>();
		for(int i = 0; i <= 10; i++) {
			BoardVO board = new BoardVO();
			board.setId(i);
			board.setTitle("제목" + i);
			board.setWriter("땃쥐" + i);
			board.setContent(i + "번 내용임");
			board.setCreatedDate(new Date());
			boardList.add(board);
		}
		return boardList;
	}
		
}