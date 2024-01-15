package com.khit.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.study.entity.Board;
import com.khit.study.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@Slf4j
public class BoardController {

	private BoardService boardService;
	
	//글쓰기 페이지 불러오기
	@GetMapping("/board/write")
	public String writeForm() {
		return "/board/write";
	}
	
	//글쓰기 처리
	@PostMapping("/board/write")
	public String write(@ModelAttribute Board board) {
		log.info("board: " + board);
		boardService.save(board);
		return "redirect:/board/";
	}
}