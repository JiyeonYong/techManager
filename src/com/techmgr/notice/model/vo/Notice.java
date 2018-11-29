package com.techmgr.notice.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeId;
	private String title;
	private String contents;
	private String authorId;
	private int comments;
	private int views;
	private Date regDate;

	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//insert 용 생성자
	public Notice(String title, String contents, String authorId) {
		super();
		this.title = title;
		this.contents = contents;
		this.authorId = authorId;
		this.comments = 0;
		this.views = 0;
	}

	public Notice(int noticeId, String title, String contents, String authorId, int comments, int views, Date regDate) {
		super();
		this.noticeId = noticeId;
		this.title = title;
		this.contents = contents;
		this.authorId = authorId;
		this.comments = comments;
		this.views = views;
		this.regDate = regDate;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getTitle() {
		return title; 
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
