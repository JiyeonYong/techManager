package com.techmgr.share.model.vo;

import java.util.ArrayList;

public class ShareData {
	private ArrayList<ShareComment> list;
	private Share share;

	public ShareData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShareData(ArrayList<ShareComment> list, Share share) {
		super();
		this.list = list;
		this.share = share;
	}

	public ArrayList<ShareComment> getList() {
		return list;
	}

	public void setList(ArrayList<ShareComment> list) {
		this.list = list;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

}
