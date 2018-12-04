package com.techmgr.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techmgr.employee.model.vo.Employee;
import com.techmgr.notice.model.service.NoticeService;
import com.techmgr.notice.model.vo.NoticeComment;

/**
 * Servlet implementation class NoticeCommentWriteServlet
 */
@WebServlet(name = "NoticeCommentWrite", urlPatterns = { "/noticeCommentWrite.do" })
public class NoticeCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		
		int noticeBoardId = Integer.parseInt(request.getParameter("noticeBoardId"));
		String comments = request.getParameter("comments");
		String userId = ((Employee)session.getAttribute("employee")).getUserId();
		String pCode = ((Employee)session.getAttribute("employee")).getpCode();
		
		NoticeComment nc = new NoticeComment();
		nc.setNoticeBoardId(noticeBoardId);
		nc.setComments(comments);
		nc.setAuthorId(userId);
		nc.setpCode(pCode);
		
		int result = new NoticeService().insertComment(nc);
		
		if(result > 0) {
			response.sendRedirect("/notice.do?noticeId=" + noticeBoardId);
		}else {
			response.sendRedirect("/views/notice/error.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
