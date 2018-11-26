package com.techmgr.notice.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.techmgr.notice.model.vo.Notice;


public interface INoticeListDao {
	public ArrayList<Notice> getCurrentPage(int currentPage, int recordCountPerPage, SqlSession session);
	public String getPageNavi(int currentPage, int recordCountPerPage, int naviCountPerPage, SqlSession session);
}
