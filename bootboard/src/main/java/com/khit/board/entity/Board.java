package com.khit.board.entity;

import java.time.LocalDateTime;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_board")
@Data
@Entity
public class Board extends BaseEntity {
	@Id //pk
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String boardTitle;
	
	@Column(length = 30, nullable = false)
	private String boardWriter;
	
	@Column(length = 2000, nullable = false)
	private String boardContent;
	
	@Column
	private Integer boardHits;
	
	//dto를 entity로 변환하는 정적 메서드
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardHits(0)
				.build();
		
				return board;
	}
	//업데이트 : 수정을 위한 정적 메서드 (id를 포함함)
	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardHits(boardDTO.getBoardHits())
				.build();
				
				return board;
			}
}
