package com.khit.board.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.khit.board.dto.BoardDTO;
import com.khit.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	//글쓰기 페이지
	@GetMapping("/write")
	public String writeForm() {
		return "/board/write";
	}
	
	//글쓰기 처리
	@PostMapping("/write")
	public String write(@ModelAttribute BoardDTO boardDTO) {
		boardService.save(boardDTO);
		return "redirect:/";
	}
	
	//글목록 
	@GetMapping("/list")
	public String getList(Model model) {
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		return "/board/list";
	}
	//글목록 (페이지)
	@GetMapping("/pagelist")
	public String getPageList
			(@PageableDefault(page = 1) Pageable pageable,
			Model model) {
		List<BoardDTO> boardDTOList = boardService.findListAll();
		model.addAttribute("boardList", boardDTOList);
		return "/board/pagelist";
	}
	
	//글 상세보기
	@GetMapping("/{id}")
	public String getBoard(@PathVariable Long id,
			Model model) {
		//조회수
		boardService.updateHits(id);
		//상세보기
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/detail";
	}
	//글 삭제
	@GetMapping("/delete/{id}")
	public String deleteBoard(@PathVariable Long id) {
		boardService.deleteById(id);
		return "redirect:/board/list";
	}
	//수정 페이지
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/update";
	}
	//수정 처리
	@PostMapping("/update/{id}")
	public String update(@PathVariable BoardDTO boardDTO) {
		boardService.update(boardDTO);
		return "redirect:/board/" + boardDTO.getId();
	}
}

