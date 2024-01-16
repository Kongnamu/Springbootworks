package com.khit.board.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@CreationTimestamp
	@Column(updatable = false) // update 불가
	private LocalDateTime createdDate;
	@CreationTimestamp
	@Column(insertable = false) // 자동생성 불가
	private LocalDateTime updatedDate;

}
