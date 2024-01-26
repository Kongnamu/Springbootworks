package com.khit.board.service;

import org.springframework.stereotype.Service;

import com.khit.board.Entity.Board;
import com.khit.board.Entity.Reply;
import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	private final ReplyRepository replyRepository;
	private final BoardRepository boardRepository;

	public void insertReply(Integer boardId, Reply reply) {
		//해당 게시글을 가져와서 
		Board board = boardRepository.findById(boardId).get();
		//board 객체를 reply에 객체 저장
		reply.setBoard(board);
		//댓글 저장
		replyRepository.save(reply);
	}
}
