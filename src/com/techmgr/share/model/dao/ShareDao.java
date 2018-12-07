package com.techmgr.share.model.dao;

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
import com.techmgr.share.model.vo.Share;
import com.techmgr.share.model.vo.ShareComment;

public class ShareDao implements IShareDao {

	@Override
	public ArrayList<Share> getCurrentPage(int currentPage, int recordCountPerPage, Connection conn) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Share share = null;
		ArrayList<Share> list = new ArrayList<Share>();

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		
		String query = "select * from (select tech_share_board.*, row_number() over(order by id desc) as rnum from tech_share_board) where rnum between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				share = new Share();
				share.setShareId(rset.getInt("id"));
				share.setTitle(rset.getString("title"));
				share.setContents(rset.getString("contents"));
				share.setAuthorId(rset.getString("author_id"));
				share.setComments(rset.getInt("comments"));
				share.setViews(rset.getInt("views"));
				share.setRegDate(rset.getDate("reg_date"));
				share.setIsBest(rset.getString("isBest"));

				list.add(share);
			}
			
			for(Share n : list) {
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

		String query = "select count(*) as totalcount from tech_share_board";

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
			sb.append("<li class = 'page-item'><a class = 'page-link' href = '/shareList.do?currentPage="
					+ (startNavi - 1) + "'> Preview </a><li>");
		}

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<li class = 'page-item active'><a class = 'page-link' href = '/shareList.do?currentPage="
						+ i + "'>" + i + "</a><li>");
				// 현재페이지는 굵게 표시
				// <a href = '/hoticeList.do?currentPage=1'><B>1</B></a>
			} else {
				sb.append("<li class = 'page-item'><a class = 'page-link' href = '/shareList.do?currentPage=" + i
						+ "'>" + i + "</a><li>");
			}
		}

		if (needNext == true) {
			sb.append("<li class = 'page-item'><a class = 'page-link' href = '/shareList.do?currentPage="
					+ (endNavi + 1) + "'> Next </a><li>");
		}

		return sb.toString();

	}

	@Override
	public int insertShare(Connection conn, Share share) {
		PreparedStatement pstmt = null;
		int shareInsertResult = 0;
		
		String query = "insert into tech_share_board values(boardNo.nextval, ?, ?, ?, ?, ?, sysdate, 'n')";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, share.getTitle());
			pstmt.setString(2, share.getContents());
			pstmt.setString(3, share.getAuthorId());
			pstmt.setInt(4, share.getComments());
			pstmt.setInt(5, share.getViews());
			
			shareInsertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return shareInsertResult;
	}

	@Override
	public Share selectOneShare(Connection conn, int shareId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Share share = null;
		
		String query = "select * from tech_share_board where id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, shareId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				share = new Share();
				
				share.setShareId(rset.getInt("id"));
				share.setTitle(rset.getString("title"));
				share.setContents(rset.getString("contents").replaceAll("\n", "<br>"));
				share.setAuthorId(rset.getString("author_id"));
				share.setComments(rset.getInt("comments"));
				share.setViews(rset.getInt("views"));
				share.setRegDate(rset.getDate("reg_date"));
				share.setIsBest(rset.getString("isBest"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(rset);
			JDBCTemplate.Close(pstmt);
		}
		
		return share;
	}

	public int updateView(Connection conn, int views, int id) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tech_share_board set views = ? where id = ?";
		
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

	public int insertComment(Connection conn, ShareComment sc) {
		PreparedStatement pstmt = null;
		int commentInsertResult = 0;
		
		String query = "insert into tech_share_comment values(shareCommentNo.nextval, ?, ?, ?, 'n' , sysdate, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, sc.getShareBoardId());
			pstmt.setString(2, sc.getComments());
			pstmt.setString(3, sc.getAuthorId());
			pstmt.setString(4, sc.getpCode());
			
			commentInsertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return commentInsertResult;
	}

	public int increaseComment(Connection conn, int shareBoardId) {
		PreparedStatement pstmt = null;
		int commentIncreaseResult = 0;
		
		String query = "update tech_share_board set comments = comments+1 where id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, shareBoardId);
			
			commentIncreaseResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return commentIncreaseResult;
	}

	public ArrayList<ShareComment> selectAllComments(Connection conn, int shareId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ShareComment sc = null;
		ArrayList<ShareComment> list = new ArrayList<ShareComment>();
		
		String query = "select * from tech_share_comment where share_board_id = ? order by id";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, shareId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				sc = new ShareComment();
				
				sc.setId(rset.getInt("id"));
				sc.setShareBoardId(rset.getInt("share_board_id"));
				sc.setComments(rset.getString("comments"));
				sc.setAuthorId(rset.getString("author_id"));
				sc.setBestcomment(rset.getString("bestcomment"));
				sc.setpCode(rset.getString("p_code"));
				sc.setRegDate(rset.getDate("reg_date"));
				
				list.add(sc);
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

	public int deleteOneShare(Connection conn, int id, String authorId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from tech_share_board where id = ? and author_id =?";
		
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
		
		String query = "delete from tech_share_comment where share_board_id = ?";
		
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
		
		String query = "delete from tech_share_comment where id = ?";
		
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

	public int decreaseComments(Connection conn, int shareId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tech_share_board set comments = comments-1 where id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, shareId);
			
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
		
		String query = "update tech_share_comment set comments = ? where id =?";
		
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

	public int updateShare(Connection conn, int shareId, String contents) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tech_share_board set contents = ? where id =?";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, contents);
			pstmt.setInt(2, shareId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		return result;
	}

	public int updateBestComment(Connection conn, int commentId, int shareId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tech_share_comment set bestcomment = 'y' where id = ? and share_board_id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, commentId);
			pstmt.setInt(2, shareId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return result;
	}

	public int updateBestBoard(Connection conn, int shareId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update tech_share_board set isbest = 'y' where id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, shareId);
			
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
