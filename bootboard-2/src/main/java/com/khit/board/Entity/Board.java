package com.khit.board.Entity;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "member")
@Setter
@Getter
@Table(name = "t_board")
@Entity
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(length = 2000, nullable = false)
	private String content;
	
	@CreationTimestamp
	private Timestamp createdDate;
	
	//Member 엔티티와 연관관계 맺기
	//ex)회원 한명에 게시글 여러개
	//다대일 매칭(fetch : 조회할때 씀 / EAGER : 전체 조회 / LAZY : 특정한 조회만 가능)
	//JoinColumn : 왜래키 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn //memberId의 name값과 일치해야함
	private Member member;
	
	//주인 관계 설정 : board가 주인이 아니다를 표기
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	@OrderBy("id desc")
	private List<Reply> replyList;
	
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.createdDate(boardDTO.getCreatedDate())
				.build();
		
				return board;
	}
	
	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId())
				.title(boardDTO.getTitle())
				.content(boardDTO.getContent())
				.createdDate(boardDTO.getCreatedDate())
				.build();
		
				return board;
	}
}
