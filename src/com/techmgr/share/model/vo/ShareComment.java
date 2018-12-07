package com.techmgr.share.model.vo;

import java.sql.Date;

public class ShareComment {
	private int id;
	private int shareBoardId;
	private String comments;
	private String bestComment;
	private String authorId;
	private Date regDate;
	private String pCode;

	public ShareComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShareComment(int id, int shareBoardId, String comments, String bestComment, String authorId, Date regDate,
			String pCode) {
		super();
		this.id = id;
		this.shareBoardId = shareBoardId;
		this.comments = comments;
		this.bestComment = bestComment;
		this.authorId = authorId;
		this.regDate = regDate;
		this.pCode = pCode;
	}

	public String getBestcomment() {
		return bestComment;
	}

	public void setBestcomment(String bestComment) {
		this.bestComment = bestComment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShareBoardId() {
		return shareBoardId;
	}

	public void setShareBoardId(int shareBoardId) {
		this.shareBoardId = shareBoardId;
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
