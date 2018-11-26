package com.techmgr.notice.model.vo;

import java.util.ArrayList;

public class NoticeData {
	private ArrayList<NoticeComment> list;
	private Notice notice;

	public NoticeData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeData(ArrayList<NoticeComment> list, Notice notice) {
		super();
		this.list = list;
		this.notice = notice;
	}

	public ArrayList<NoticeComment> getList() {
		return list;
	}

	public void setList(ArrayList<NoticeComment> list) {
		this.list = list;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

}
