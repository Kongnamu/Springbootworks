package com.khit.board.dto;

import java.time.LocalDateTime;

import com.khit.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	
	private Long id;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Integer boardHits;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	//entity를 dto로 변환하는 메서드
	public static BoardDTO toSaveDTO(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				.id(board.getId())
				.boardTitle(board.getBoardTitle())
				.boardWriter(board.getBoardWriter())
				.boardContent(board.getBoardContent())
				.boardHits(board.getBoardHits())
				.createdDate(board.getCreatedDate())
				.updatedDate(board.getUpdatedDate())
				.build();
		
		return boardDTO;
	}
	
}