package com.khit.board.dto;

import java.sql.Timestamp;
import java.util.List;

import com.khit.board.Entity.Board;
import com.khit.board.Entity.Member;
import com.khit.board.Entity.Reply;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardDTO {

	private Integer id;
	
	private String title;
	
	private String content;
	
	private Member member;
	
	private List<Reply> replyList;
	
	private Timestamp createdDate;
	
	private Timestamp updateDate;
	
	//dto -> entity로 변환
	public static BoardDTO toSaveEntity(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.content(board.getContent())
				.member(board.getMember())
				.replyList(board.getReplyList())
				.createdDate(board.getCreatedDate())
				.updateDate(board.getUpdateDate())
				.build();
		
		return boardDTO;
	}
}
