package com.techmgr.notice.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.techmgr.common.JDBCTemplate;
import com.techmgr.file.model.dao.FileDao;
import com.techmgr.file.model.vo.FileData;
import com.techmgr.notice.model.dao.NoticeDao;
import com.techmgr.notice.model.vo.Notice;
import com.techmgr.notice.model.vo.PageData;

public class NoticeService implements INoticeService {
	
	
	private SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";
		
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session;
	}
	
	@Override
	public PageData selectAllNotice(int currentPage) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int recordCountPerPage = 20;
		int naviCountPerPage = 10;
		
		ArrayList<Notice> list = new NoticeDao().getCurrentPage(currentPage, recordCountPerPage, conn);
		
		String pageNavi = new NoticeDao().getPageNavi(currentPage, recordCountPerPage, naviCountPerPage, conn);
		
		PageData pd = null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd = new PageData(list, pageNavi);
		}
		
		JDBCTemplate.Close(conn);
		
		return pd;
	
	}

	@Override
	public int insertNotice(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		
		int noticeInsertResult = new NoticeDao().insertNotice(conn, notice);
		
		if(noticeInsertResult > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.Close(conn);
		
		return noticeInsertResult;
	}

	@Override
	public Notice selectOneNotice(int noticeId) {
		Connection conn = JDBCTemplate.getConnection();
		
		Notice notice = new NoticeDao().selectOneNotice(conn, noticeId);
		
		if(notice != null) {
			int views = notice.getViews();
			int id = notice.getNoticeId();
			views++;
			int result = new NoticeDao().updateView(conn, views, id);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
		}
		
		JDBCTemplate.Close(conn);
		
		return notice;
	}

}
