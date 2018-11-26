package com.techmgr.notice.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.techmgr.notice.model.dao.NoticeListDao;
import com.techmgr.notice.model.vo.Notice;
import com.techmgr.notice.model.vo.PageData;

public class NoticeListService implements INoticeListService {
	
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
		
		SqlSession session = getSqlSession();
		
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		ArrayList<Notice> list = new NoticeListDao().getCurrentPage(currentPage, recordCountPerPage, session);
		
		for(Notice n : list) {
			System.out.println(n.getTitle());
		}
		
		String pageNavi = new NoticeListDao().getPageNavi(currentPage, recordCountPerPage, naviCountPerPage, session);
		
		PageData pd = null;
		
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd = new PageData(list, pageNavi);
		}
		
		session.close();
		
		return pd;
	}

}
