package com.khit.study.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Board {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 400, nullable = false)
	private String title;
	
	@Column(length = 30, nullable = false)
	private String writer;
	
	@Column(length = 4000, nullable = false)
	private String content;
	
	@CreationTimestamp
	private Timestamp createdDate;
}
