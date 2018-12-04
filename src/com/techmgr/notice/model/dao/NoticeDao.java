package com.techmgr.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.techmgr.common.JDBCTemplate;
import com.techmgr.file.model.vo.FileData;
import com.techmgr.notice.model.vo.Notice;
import com.techmgr.notice.model.vo.NoticeComment;

public class NoticeDao implements INoticeDao {

	@Override
	public ArrayList<Notice> getCurrentPage(int currentPage, int recordCountPerPage, Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		ArrayList<Notice> list = new ArrayList<Notice>();

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		
		String query = "select * from (select notice_board.*, row_number() over(order by id desc) as rnum from notice_board) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				notice = new Notice();
				notice.setNoticeId(rset.getInt("id"));
				notice.setTitle(rset.getString("title"));
				notice.setContents(rset.getString("contents"));
				notice.setAuthorId(rset.getString("author_id"));
				notice.setComments(rset.getInt("comments"));
				notice.setViews(rset.getInt("views"));
				notice.setRegDate(rset.getDate("reg_date"));

				list.add(notice);
			}
			
			for(Notice n : list) {
				System.out.println(n.getTitle());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(rset);
			JDBCTemplate.Close(pstmt);
		}

		return list;

	}

	@Override
	public String getPageNavi(int currentPage, int recordCountPerPage, int naviCountPerPage, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int recordTotalCount = 0;

		String query = "select count(*) as totalcount from notice_board";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				recordTotalCount = rset.getInt("totalcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(rset);
			JDBCTemplate.Close(pstmt);
		}

		int pageTotalCount = 0;

		if (recordTotalCount % recordCountPerPage != 0) {
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		} else {
			pageTotalCount = (recordTotalCount / recordCountPerPage);
		}

		if (currentPage < 1) {
			currentPage = 1;
		} else if (currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}

		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;

		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		boolean needPrev = true;
		boolean needNext = true;

		if (startNavi == 1) {
			needPrev = false;
		}

		if (endNavi == pageTotalCount) {
			needNext = false;
		}

		StringBuilder sb = new StringBuilder();

		if (needPrev == true) {
			sb.append("<li class = 'page-item'><a class = 'page-link' href = '/noticeList.do?currentPage="
					+ (startNavi - 1) + "'> Preview </a><li>");
		}

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<li class = 'page-item active'><a class = 'page-link' href = '/noticeList.do?currentPage="
						+ i + "'>" + i + "</a><li>");
				// 현재페이지는 굵게 표시
				// <a href = '/hoticeList.do?currentPage=1'><B>1</B></a>
			} else {
				sb.append("<li class = 'page-item'><a class = 'page-link' href = '/noticeList.do?currentPage=" + i
						+ "'>" + i + "</a><li>");
			}
		}

		if (needNext == true) {
			sb.append("<li class = 'page-item'><a class = 'page-link' href = '/noticeList.do?currentPage="
					+ (endNavi + 1) + "'> Next </a><li>");
		}

		return sb.toString();

	}

	@Override
	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int noticeInsertResult = 0;
		
		String query = "insert into notice_board values(boardNo.nextval, ?, ?, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContents());
			pstmt.setString(3, notice.getAuthorId());
			pstmt.setInt(4, notice.getComments());
			pstmt.setInt(5, notice.getViews());
			
			noticeInsertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return noticeInsertResult;
	}

	@Override
	public Notice selectOneNotice(Connection conn, int noticeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		
		String query = "select * from notice_board where id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice();
				
				notice.setNoticeId(rset.getInt("id"));
				notice.setTitle(rset.getString("title"));
				notice.setContents(rset.getString("contents").replaceAll("\n", "<br>"));
				notice.setAuthorId(rset.getString("author_id"));
				notice.setComments(rset.getInt("comments"));
				notice.setViews(rset.getInt("views"));
				notice.setRegDate(rset.getDate("reg_date"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notice;
	}

	public int updateView(Connection conn, int views, int id) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update notice_board set views = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, views);
			pstmt.setInt(2, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return result;
	}

	public int insertComment(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int commentInsertResult = 0;
		
		String query = "insert into notice_comment values(noticeCommentNo.nextval, ?, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, nc.getNoticeBoardId());
			pstmt.setString(2, nc.getComments());
			pstmt.setString(3, nc.getAuthorId());
			pstmt.setString(4, nc.getpCode());
			
			commentInsertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return commentInsertResult;
	}

	public int increaseComment(Connection conn, int noticeBoardId) {
		PreparedStatement pstmt = null;
		int commentIncreaseResult = 0;
		
		String query = "update notice_board set comments = comments+1 where id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeBoardId);
			
			commentIncreaseResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return commentIncreaseResult;
	}

	public ArrayList<NoticeComment> selectAllComments(Connection conn, int noticeId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		NoticeComment nc = null;
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		
		String query = "select * from notice_comment where notice_board_id = ? order by id";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				nc = new NoticeComment();
				
				nc.setId(rset.getInt("id"));
				nc.setNoticeBoardId(rset.getInt("notice_board_id"));
				nc.setComments(rset.getString("comments"));
				nc.setAuthorId(rset.getString("author_id"));
				nc.setpCode(rset.getString("p_code"));
				nc.setRegDate(rset.getDate("reg_date"));
				
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(rset);
			JDBCTemplate.Close(pstmt);
		}
		
		return list;
	}

	public int deleteOneNotice(Connection conn, int id, String authorId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from notice_board where id = ? and author_id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, authorId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return result;
	}

	public int deleteComments(Connection conn, int id) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from notice_comment where notice_board_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return result;
	}

	public int deleteOneComment(Connection conn, int commentId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from notice_comment where id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, commentId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		return result;
	}

	public int decreaseComments(Connection conn, int noticeId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update notice_board set comments = comments-1 where id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return result;
	}

	public int updateComment(Connection conn, int commentId, String comments) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update notice_comment set comments = ? where id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, comments);
			pstmt.setInt(2, commentId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return result;
	}

	public int updateNotice(Connection conn, int noticeId, String contents) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update notice_board set contents = ? where id =?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, contents);
			pstmt.setInt(2, noticeId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		return result;
	}
	

}
