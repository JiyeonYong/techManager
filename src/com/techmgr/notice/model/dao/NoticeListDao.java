package com.techmgr.notice.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.techmgr.notice.model.vo.Notice;

public class NoticeListDao implements INoticeListDao {

	@Override
	public ArrayList<Notice> getCurrentPage(int currentPage, int recordCountPerPage, SqlSession session) {

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;
		
		System.out.println(start);
		System.out.println(end);

//		HashMap<String, Integer> parameters = new HashMap<String, Integer>();
		RowBounds rowBounds = new RowBounds((currentPage-1)*recordCountPerPage,recordCountPerPage);
//		parameters.put("start", start);
//		parameters.put("end", end);

		List<Notice> list = session.selectList("notice.getCurrentPage1", null, rowBounds);
		
		for(Notice n : list) {
			System.out.println(n.getTitle());
		}
		return (ArrayList<Notice>) list;
	}

	@Override
	public String getPageNavi(int currentPage, int recordCountPerPage, int naviCountPerPage, SqlSession session) {

		int recordTotalCount = 0;

		recordTotalCount = session.selectOne("notice.getPageNavi", null);

		System.out.println("getPageNavi totalCount test : " + recordTotalCount);

		int pageTotalCount = 0;

		if (recordTotalCount % recordCountPerPage != 0) {
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		} else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
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
			} else {
				sb.append("<li class = 'page-item'><a class = 'page-link' href = '/noticeList.do?currentPage=" + i
						+ "'>" + i + "</a><li>");
			}
		}

		if (needNext == true) {
			sb.append("<li class = 'page-item'><a class = 'page-link' href = '/noticeList.do?currentPage="
					+ (endNavi + 1) + "'> Next </a><li>");
		}

		System.out.println(sb.toString());
		return sb.toString();
	}

}
