package com.khit.board.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.khit.board.Entity.Member;
import com.khit.board.Entity.Role;
import com.khit.board.repository.MemberRepository;

@SpringBootTest
public class PasswordEncoderTest {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder pwEncoder;
	
	/* @Test
	public void testInsertData() {
		//일반 회원
		Member member = new Member();
		member.setMemberId("khit123");
		member.setPassword(pwEncoder.encode("khit123"));
		member.setName("배고팡");
		member.setRole(Role.MEMBER);
		
		memberRepository.save(member);
	} */
}
