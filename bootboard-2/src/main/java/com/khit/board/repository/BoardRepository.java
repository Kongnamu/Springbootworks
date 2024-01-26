package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.Entity.Board;
import com.khit.board.config.SecurityUser;

public interface BoardRepository extends JpaRepository<Board, Integer>{



	
}
