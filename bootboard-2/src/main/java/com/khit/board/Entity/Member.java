package com.khit.board.Entity;

import java.util.ArrayList;
import java.util.List;

import com.khit.board.dto.MemberDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "boardList") //순환 참조 방지
@Setter
@Getter
@Table(name = "t_member")
@Entity
public class Member extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //회원 번호
	
	@Column(name = "member_id", unique = true)
	private String memberId; //아이디
	
	@Column(nullable = false)
	private String password; //비밀번호
	
	@Column(nullable = false, length = 30)
	private String name;
	
	@Column
	//private String role; //권한
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	//Board와 관계맺기
	//주인 설정 (Board가 주인) : mappedBy 사용 
	//cascade : 참조된 객체가 삭제되면 참조하는 객체도 삭제함
	@OneToMany(mappedBy = "member",
			//외래키 참조로 인해 삭제 안되기 때문에 cascade 설정 필요
			cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<>();
	
	//reply와 연관 관계 설정
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Reply> replyList;
	
	
	//dto(view에서 전달된 입력값)를 entity(db)에 저장
	//회원가입 : id가 자동생성되므로 dto에서 가져올 id가 없음 -> id 빌드 필요 없음
	public static Member toSaveEntity(MemberDTO memberDTO) {
		Member member = Member.builder()
				.memberId(memberDTO.getMemberId())
				.password(memberDTO.getPassword())
				.name(memberDTO.getName())
				.role(memberDTO.getRole())
				.build();
				
				return member;
	}
	//업데이트 : id가 발급되어있는 상태로 빌드에 추가하여야 함
	public static Member toSaveUpdate(MemberDTO memberDTO) {
		Member member = Member.builder()
				.id(memberDTO.getId())
				.memberId(memberDTO.getMemberId())
				.password(memberDTO.getPassword())
				.name(memberDTO.getName())
				.role(memberDTO.getRole())
				.build();
				
				return member;
	}


}
