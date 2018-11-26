package com.techmgr.notice.model.vo;

import java.sql.Date;

public class NoticeComment {
	private int noticeCommentId;
	private int noticeId;
	private String comments;
	private String authorId;
	private String pCode;
	private Date regDate;

	public NoticeComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeComment(int noticeCommentId, int noticeId, String comments, String authorId, String pCode,
			Date regDate) {
		super();
		this.noticeCommentId = noticeCommentId;
		this.noticeId = noticeId;
		this.comments = comments;
		this.authorId = authorId;
		this.pCode = pCode;
		this.regDate = regDate;
	}

	public int getNoticeCommentId() {
		return noticeCommentId;
	}

	public void setNoticeCommentId(int noticeCommentId) {
		this.noticeCommentId = noticeCommentId;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
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
