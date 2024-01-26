package com.khit.board.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.khit.board.Entity.Member;
import com.khit.board.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//db에 있는 회원 정보를 조회
		//UserDetails 타입의 객체를 반환한다.
		Optional<Member> findMember = memberRepository.findByMemberId(username); //username을 db에서 찾음
		if(findMember.isEmpty()) {
			throw new UsernameNotFoundException(username + "사용자 없음"); //db에 없으면
		}else { //db에 있으면
			Member member = findMember.get();
			return new SecurityUser(member);
		}
		
	}
	

}
