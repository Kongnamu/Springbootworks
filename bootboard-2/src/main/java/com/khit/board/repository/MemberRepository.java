package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.Entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	//select * from member where member_id = ?;
	Optional<Member> findByMemberId(String string);

}
