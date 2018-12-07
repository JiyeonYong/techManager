package com.techmgr.share.model.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.techmgr.file.model.vo.FileData;
import com.techmgr.share.model.vo.Share;



public interface IShareDao {
	public ArrayList<Share> getCurrentPage(int currentPage, int recordCountPerPage, Connection conn);
	public String getPageNavi(int currentPage, int recordCountPerPage, int naviCountPerPage, Connection conn);
	public int insertShare(Connection conn, Share notice);
	public Share selectOneShare(Connection conn, int noticeId);
}
