package com.khit.board.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.Entity.Member;
import com.khit.board.config.SecurityUser;
import com.khit.board.dto.MemberDTO;
import com.khit.board.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	//로그인 페이지
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
	//로그인 처리
	/* @PostMapping("/member/login")
	public String login(@ModelAttribute Member member, HttpSession session) {
		Member loginMember = memberService.login(member);
		if(loginMember != null
				&& loginMember.getPassword().equals(member.getPassword())) {
			//아이디 비번 일치해서 로그인 되면 세션 발급
			session.setAttribute("sessMemberId", loginMember.getMemberId());
			log.info(loginMember.toString());
			return "main";
		}else {
			return "member/login";
		}
	} */
	
	//메인 페이지
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	//로그아웃
	/* @GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}*/
	//회원 가입 페이지
	@GetMapping("/member/join")
	public String joinForm(MemberDTO memberDTO) {
		return "member/join";
	}
	//회원가입 처리
	// @valid : 유효성 검사
	// BindingResult : 에러 처리 클래스
	@PostMapping("/member/join")
	public String join(@Valid MemberDTO memberDTO,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			//에러가 있으면 회원 가입 페이지에 머무름
			return "member/join";
		}
		memberService.save(memberDTO);
		return "redirect:/login";
	}
	//회원 목록
	@GetMapping("/member/list")
	public String memberList(Model model) {
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "member/list";
	}
	//회원 상세보기
	@GetMapping("/member/{id}")
	public String memberDetail(@PathVariable Integer id,
			Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "member/detail";
	}
	//회원 삭제
	@GetMapping("/member/delete/{id}")
	public String delete(@PathVariable Integer id) {
		memberService.deleteById(id);
		return "redirect:/";
	}
	//회원 수정 페이지
	@GetMapping("/member/update")
	public String updateForm(@AuthenticationPrincipal SecurityUser principal,
			Model model) {
		MemberDTO memberDTO = memberService.findByMemberId(principal);
		model.addAttribute("member", memberDTO);
		return "member/update";
	}
	//수정 처리
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/" + memberDTO.getId() ;
	}

}
