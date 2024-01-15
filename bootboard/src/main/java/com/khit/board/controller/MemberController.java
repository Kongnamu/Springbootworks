package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.dto.MemberDTO;
import com.khit.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	//회원 가입 화면
	@GetMapping("/member/join")
	public String joinForm() {
		return "/member/join"; //join.html
	}
	//회원 가입 처리
	@PostMapping("/member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		System.out.println("memberDTO: " + memberDTO);
		memberService.save(memberDTO);
		return "redirect:/member/login";
	}
	//회원 목록
	@GetMapping("/member/list")
	public String getList(Model model) {
		//db에서 꺼내서 memberDTO로 변환할 것
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "/member/list";
	}
	
	//회원 상세보기
	//@PathVariable : 경로에 변수를 할당
	@GetMapping("/member/{id}")
	public String getMember(@PathVariable Long id,
			Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "/member/detail"; 
	}
	//회원 삭제
	@GetMapping("/member/delete/{id}")
	public String deleteMember(@PathVariable Long id) {
		memberService.deleteById(id);
		return "redirect:/member/list";
	}
	//로그인
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";
	}
	//로그인 처리
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO,
			HttpSession session, Model model) {
		MemberDTO loginMember = memberService.login(memberDTO);
		//로그인한 결과(객체가 있으면 로그인 됨, 없으면 다시 로그인)
		if(loginMember != null) {
			session.setAttribute("sessionEmail", loginMember.getMemberEmail());
			return "main";
		}else {
			String error = "아이디 혹은 비밀번호를 확인해주세요";
			model.addAttribute("error",  error);
			return "/member/login"; //main.html
		}
	}
	//로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //모든 세션 삭제
		return "redirect:/";
	}
	//회원 정보 수정페이지
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String email = (String)session.getAttribute("sessionEmail");
		MemberDTO memberDTO = memberService.finByMemberEmail(email);
		model.addAttribute("member", memberDTO);
		return "/member/update";
	}
	//수정 처리
	@PostMapping("/member/update")
	public String update(MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/" + memberDTO.getId(); //상세보기로 보냄
	}
}
