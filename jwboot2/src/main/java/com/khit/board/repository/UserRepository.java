package com.khit.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	//메서드 : save, findall, findbyid, deletebyid
}
