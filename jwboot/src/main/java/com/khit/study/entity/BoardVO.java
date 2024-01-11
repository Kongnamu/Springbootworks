package com.khit.study.entity;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int id;
	private String title;
	private String writer;
	private String content;
	private Date createdDate;
}
