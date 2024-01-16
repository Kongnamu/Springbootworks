package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor //생성자 주입 방식 : final을 꼭 붙임
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
		//db안으로 저장 : entity를 저장
		//dto를 entity로 변환 메서드 사용 필요 : Member에서
		Member member = Member.toSaveEntity(memberDTO);
		memberRepository.save(member);
	}
	
	public List<MemberDTO> findAll() {
		//db에서 member 엔티티를 꺼내와서
		List<Member> memberList = memberRepository.findAll();
		//변환 메서드 필요
		//member를 담을 비어있는 dto리스트를 생성
		List<MemberDTO> memberDTOList = new ArrayList<>();
		for(Member member : memberList) { //memberList를 반복하면서
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
		//컨트롤러에 dtoList로 보내기
		return memberDTOList;
	}
	
	public MemberDTO findById(Long id) {
		//db에서 멤버 1개 꺼내오기
		//findById(id).get : optional하면 .get 빼야함
		//id가 없을 때 오류 처리
		Optional<Member> member = memberRepository.findById(id);
		if(member.isPresent()) {
			//entity -> dto 변환 : optional이 들어가면서 get()이 붙음
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
			return memberDTO;
		}else {
			throw new BootBoardException("찾는 데이터가 없습니다.");
		}
		
	}

	public void deleteById(Long id) {
		//삭제 : deleteById();
		memberRepository.deleteById(id);
	}
	//로그인 처리
	public MemberDTO login(MemberDTO memberDTO) {
		//1. email로 회원 조회(이메일이 id니까)후 비밀번호까지 비교
		Optional<Member> memberEmail = 
				memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
		if(memberEmail.isPresent()) {
			//조회 결과 이메일이 있음 : 1건을 가져온다
			Member member = memberEmail.get();
			//비밀번호 일치
			if(member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				//entity를 dto로 변환
				MemberDTO dto = MemberDTO.toSaveDTO(member);
				return dto;
			}else {
				//비밀번호 불일치
				return null;
			}
		}else {
			return null;
		}
	}
	//회원 정보 수정페이지
	public MemberDTO finByMemberEmail(String email) {
		//db에서 이메일로 검색한 회원 객체를 가져오고
		Member member = memberRepository.findByMemberEmail(email).get();
		//회원 객체(entity)를 dto로 변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
		return memberDTO;
	}
	//회원 정보 수정 처리
	public void update(MemberDTO memberDTO) {
		//가입할때는 id가 없음, 수정할때는 id가 있음 -> id를 넣어줘야함
		Member member = Member.toUpdateEntity(memberDTO);
		//id가 있는 엔티티의 메서드가 필요함
		memberRepository.save(member);
	}

	public String checkEmail(String memberEmail) {
		//db에 있는 이메일 조회 -> 없으면 "OK" 있으면 "NO"
		Optional<Member> findMember = 
				memberRepository.findByMemberEmail(memberEmail);
		if(findMember.isEmpty()) { //db에 회원이 없으면 가입해도 되는 문자("OK" 반환
			return "OK";
		}else {
			return "NO";
		}
		
	}

	

	
}
