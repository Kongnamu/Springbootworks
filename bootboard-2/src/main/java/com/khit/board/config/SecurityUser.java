package com.khit.board.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.khit.board.Entity.Member;

public class SecurityUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private Member member;
	
	//set
	//3가지 파라미터 필요: username, password, role
	//암호화 안된 상태 : {noop} + member.getPassword()으로 표현
	public SecurityUser(Member member) {
		super(member.getMemberId(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}
	
	//get
	//글쓰기 처리때 getmember()로 보내기 위함
	public Member getmember() {
		return member;
	}

}
