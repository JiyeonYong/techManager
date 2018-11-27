package com.techmgr.notice.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.techmgr.common.JDBCTemplate;
import com.techmgr.notice.model.dao.NoticeListDao;
import com.techmgr.notice.model.vo.Notice;
import com.techmgr.notice.model.vo.PageData;

public class NoticeListService implements INoticeListService {
	
	@Override
	public PageData selectAllNotice(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 20;
		int naviCountPerPage = 10;
		
		ArrayList<Notice> list = new NoticeListDao().getCurrentPage(currentPage, recordCountPerPage, conn);
		
		String pageNavi = new NoticeListDao().getPageNavi(currentPage, recordCountPerPage, naviCountPerPage, conn);
		
		PageData pd = null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd = new PageData(list, pageNavi);
		}
		
		JDBCTemplate.Close(conn);
		
		return pd;
	
	}

}
