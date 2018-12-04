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
import com.techmgr.notice.model.vo.NoticeComment;
import com.techmgr.notice.model.vo.NoticeData;
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

		int recordCountPerPage = 10;
		int naviCountPerPage = 5;

		ArrayList<Notice> list = new NoticeDao().getCurrentPage(currentPage, recordCountPerPage, conn);

		String pageNavi = new NoticeDao().getPageNavi(currentPage, recordCountPerPage, naviCountPerPage, conn);

		PageData pd = null;

		if (!list.isEmpty() && !pageNavi.isEmpty()) {
			pd = new PageData(list, pageNavi);
		}

		JDBCTemplate.Close(conn);

		return pd;

	}

	@Override
	public int insertNotice(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();

		int noticeInsertResult = new NoticeDao().insertNotice(conn, notice);

		if (noticeInsertResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return noticeInsertResult;
	}

	@Override
	public NoticeData selectOneNotice(int noticeId) {
		Connection conn = JDBCTemplate.getConnection();

		// notice contents
		Notice notice = new NoticeDao().selectOneNotice(conn, noticeId);

		// comment list
		ArrayList<NoticeComment> list = new NoticeDao().selectAllComments(conn, noticeId);

		if (notice != null) {
			int views = notice.getViews();
			int id = notice.getNoticeId();
			views++;
			int result = new NoticeDao().updateView(conn, views, id);

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}

		NoticeData nd = new NoticeData();
		nd.setNotice(notice);
		nd.setList(list);

		if (list != null) {
			for (NoticeComment nc : nd.getList()) {
				System.out.println(nc.getComments());
			}
		}
		JDBCTemplate.Close(conn);

		return nd;
	}

	public int insertComment(NoticeComment nc) {
		Connection conn = JDBCTemplate.getConnection();

		int commentInsertResult = new NoticeDao().insertComment(conn, nc);

		int commentIncreaseResult = new NoticeDao().increaseComment(conn, nc.getNoticeBoardId());

		if (commentInsertResult > 0 && commentIncreaseResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return commentIncreaseResult;

	}

	public int deleteOneNotice(int id, String authorId) {
		Connection conn = JDBCTemplate.getConnection();

		// delete comment
		int commentDeleteResult = new NoticeDao().deleteComments(conn, id);

		// delete notice
		int noticeDeleteResult = new NoticeDao().deleteOneNotice(conn, id, authorId);

		if (noticeDeleteResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return noticeDeleteResult;
	}

	public int deleteOneComment(int commentId, int noticeId) {
		Connection conn = JDBCTemplate.getConnection();

		// delete comment
		int deleteCommentResult = new NoticeDao().deleteOneComment(conn, commentId);

		// update notice.comments
		int commentDecreaseResult = new NoticeDao().decreaseComments(conn, noticeId);
		if (deleteCommentResult > 0 && commentDecreaseResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return deleteCommentResult;
	}

	public int updateComment(int commentId, String comments) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().updateComment(conn, commentId, comments);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.Close(conn);
		
		return result;
	}

	public int updateNotice(int noticeId, String contents) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().updateNotice(conn, noticeId, contents);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.Close(conn);
		
		return result;
	}

}
