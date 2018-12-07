package com.techmgr.share.model.vo;

import java.util.ArrayList;

public class PageData {
	private ArrayList<Share> list;
	private String pageNavi;

	public PageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageData(ArrayList<Share> list, String pageNavi) {
		super();
		this.list = list;
		this.pageNavi = pageNavi;
	}

	public ArrayList<Share> getList() {
		return list;
	}

	public void setList(ArrayList<Share> list) {
		this.list = list;
	}

	public String getPageNavi() {
		return pageNavi;
	}

	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}

}
