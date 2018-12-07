package com.techmgr.share.model.vo;

import java.sql.Date;

public class Share {
	private int shareId;
	private String title;
	private String contents;
	private String authorId;
	private int comments;
	private int views;
	private Date regDate;
	private String isBest;

	public Share() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Share(int shareId, String title, String contents, String authorId, int comments, int views, Date regDate, String isBest) {
		super();
		this.shareId = shareId;
		this.title = title;
		this.contents = contents;
		this.authorId = authorId;
		this.comments = comments;
		this.views = views;
		this.regDate = regDate;
		this.isBest = isBest;
	}

	public Share(String title, String contents, String authorId) {
		super();
		this.title = title;
		this.contents = contents;
		this.authorId = authorId;
	}

	public int getShareId() {
		return shareId;
	}

	public void setShareId(int shareId) {
		this.shareId = shareId;
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

	public String getIsBest() {
		return isBest;
	}

	public void setIsBest(String isBest) {
		this.isBest = isBest;
	}

}
