package com.techmgr.notice.model.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.techmgr.file.model.vo.FileData;
import com.techmgr.notice.model.vo.Notice;


public interface INoticeDao {
	public ArrayList<Notice> getCurrentPage(int currentPage, int recordCountPerPage, Connection conn);
	public String getPageNavi(int currentPage, int recordCountPerPage, int naviCountPerPage, Connection conn);
	public int insertNotice(Connection conn, Notice notice);
	public Notice selectOneNotice(Connection conn, int noticeId);
}
