package com.techmgr.notice.model.vo;

import java.sql.Date;

public class NoticeComment {
	private int id;
	private int noticeBoardId;
	private String comments;
	private String authorId;
	private String pCode;
	private Date regDate;

	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeComment(int id, int noticeBoardId, String comments, String authorId, String pCode, Date regDate) {
		super();
		this.id = id;
		this.noticeBoardId = noticeBoardId;
		this.comments = comments;
		this.authorId = authorId;
		this.pCode = pCode;
		this.regDate = regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNoticeBoardId() {
		return noticeBoardId;
	}

	public void setNoticeBoardId(int noticeBoardId) {
		this.noticeBoardId = noticeBoardId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
