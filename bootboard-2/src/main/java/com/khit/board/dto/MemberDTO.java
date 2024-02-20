package com.khit.board.dto;

import java.sql.Timestamp;

import com.khit.board.Entity.Member;
import com.khit.board.Entity.Role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDTO {

	private Integer id;
	
	@Size(min=4, max=20)
	@NotEmpty(message = "사용자 ID는 필수 항목입니다.")
	private String memberId;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String password;
	
	@NotEmpty(message = "이름은 필수 항목입니다.")
	private String name;
	
	private Role role;
	
	private Timestamp createdDate;
	
	private Timestamp UpdatedDate;
	
	//entity -> dto(view로 보여주기 위해)로 변환 : 
	//회원 목록, 상세보기 할 때 (db에서 꺼내오기 때문에 select개념임)
	public static MemberDTO toSaveDTO(Member member) {
		MemberDTO memberDTO = MemberDTO.builder()
				.id(member.getId())
				.memberId(member.getMemberId())
				.password(member.getPassword())
			    .name(member.getName())
			    .role(member.getRole())
			    .createdDate(member.getCreatedDate())
			    .UpdatedDate(member.getUpdateDate())
			    .build();
		
		return memberDTO;
	}
}
