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
		int result = 0;
		
		String query = "insert into notice_board values(boardNo.nextval, ?, ?, ?, ?, ?, sysdate)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContents());
			pstmt.setString(3, notice.getAuthorId());
			pstmt.setInt(4, notice.getComments());
			pstmt.setInt(5, notice.getViews());
			
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
