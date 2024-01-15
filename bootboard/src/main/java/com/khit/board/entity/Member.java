package com.khit.board.entity;

import com.khit.board.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "tbl_member")
@Entity
public class Member {
	@Id //Primary key(기본키)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true) //유일성 부여, 중복검사
	private String memberEmail;
	
	@Column(nullable = false) //필수 입력
	private String memberPassword;
	
	@Column(length = 30, nullable = false)
	private String memberName;
	
	@Column
	private int memberAge;
	
	//[회원가입] dto를 매개로 받아서 entity에 저장하는 메서드 생성
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = new Member();
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPassword(memberDTO.getMemberPassword());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		
		return member;
	}
	//업데이트 : 수정을 위한 정적 메서드 (id를 포함함)
	public static Member toUpdateEntity(MemberDTO memberDTO) {
		Member member = new Member();
		member.setId(memberDTO.getId());
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPassword(memberDTO.getMemberPassword());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		
		return member;
	}
}
