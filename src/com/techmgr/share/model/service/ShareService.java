package com.techmgr.share.model.service;

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
import com.techmgr.share.model.dao.ShareDao;
import com.techmgr.share.model.vo.PageData;
import com.techmgr.share.model.vo.Share;
import com.techmgr.share.model.vo.ShareComment;
import com.techmgr.share.model.vo.ShareData;

public class ShareService implements IShareService {

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
	public PageData selectAllShare(int currentPage) {

		Connection conn = JDBCTemplate.getConnection();

		int recordCountPerPage = 10;
		int naviCountPerPage = 5;

		ArrayList<Share> list = new ShareDao().getCurrentPage(currentPage, recordCountPerPage, conn);

		String pageNavi = new ShareDao().getPageNavi(currentPage, recordCountPerPage, naviCountPerPage, conn);

		PageData pd = null;

		if (!list.isEmpty() && !pageNavi.isEmpty()) {
			pd = new PageData(list, pageNavi);
		}

		JDBCTemplate.Close(conn);

		return pd;

	}

	@Override
	public int insertShare(Share share) {
		Connection conn = JDBCTemplate.getConnection();

		int shareInsertResult = new ShareDao().insertShare(conn, share);

		if (shareInsertResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return shareInsertResult;
	}

	@Override
	public ShareData selectOneShare(int shareId) {
		Connection conn = JDBCTemplate.getConnection();

		// Share contents
		Share share = new ShareDao().selectOneShare(conn, shareId);

		// comment list
		ArrayList<ShareComment> list = new ShareDao().selectAllComments(conn, shareId);

		if (share != null) {
			int views = share.getViews();
			int id = share.getShareId();
			views++;
			int result = new ShareDao().updateView(conn, views, id);

			if (result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}

		ShareData sd = new ShareData();
		sd.setShare(share);
		sd.setList(list);

		if (list != null) {
			for (ShareComment sc : sd.getList()) {
				System.out.println(sc.getComments());
			}
		}
		JDBCTemplate.Close(conn);

		return sd;
	}

	public int insertComment(ShareComment nc) {
		Connection conn = JDBCTemplate.getConnection();

		int commentInsertResult = new ShareDao().insertComment(conn, nc);

		int commentIncreaseResult = new ShareDao().increaseComment(conn, nc.getShareBoardId());

		if (commentInsertResult > 0 && commentIncreaseResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return commentIncreaseResult;

	}

	public int deleteOneShare(int id, String authorId) {
		Connection conn = JDBCTemplate.getConnection();

		// delete comment
		int commentDeleteResult = new ShareDao().deleteComments(conn, id);

		// delete share
		int shareDeleteResult = new ShareDao().deleteOneShare(conn, id, authorId);

		if (shareDeleteResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return shareDeleteResult;
	}

	public int deleteOneComment(int commentId, int shareBoardId) {
		Connection conn = JDBCTemplate.getConnection();

		// delete comment
		int deleteCommentResult = new ShareDao().deleteOneComment(conn, commentId);

		// update share.comments
		int commentDecreaseResult = new ShareDao().decreaseComments(conn, shareBoardId);
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

		int result = new ShareDao().updateComment(conn, commentId, comments);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return result;
	}

	public int updateShare(int shareBoardId, String contents) {
		Connection conn = JDBCTemplate.getConnection();

		int result = new ShareDao().updateShare(conn, shareBoardId, contents);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return result;
	}

	public int updateBestComment(int commentId, int shareId) {
		Connection conn = JDBCTemplate.getConnection();

		int updateBestCommentResult = new ShareDao().updateBestComment(conn, commentId, shareId);

		int updateBestBoardResult = new ShareDao().updateBestBoard(conn, shareId);

		if (updateBestCommentResult > 0 && updateBestBoardResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.Close(conn);

		return updateBestCommentResult;
	}

}
