package com.techmgr.share.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techmgr.employee.model.vo.Employee;
import com.techmgr.share.model.service.ShareService;
import com.techmgr.share.model.vo.ShareComment;

/**
 * Servlet implementation class NoticeCommentWriteServlet
 */
@WebServlet(name = "ShareCommentWrite", urlPatterns = { "/shareCommentWrite.do" })
public class ShareCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		
		int shareBoardId = Integer.parseInt(request.getParameter("shareBoardId"));
		String comments = request.getParameter("comments");
		String userId = ((Employee)session.getAttribute("employee")).getUserId();
		String pCode = ((Employee)session.getAttribute("employee")).getpCode();
		
		ShareComment sc = new ShareComment();
		sc.setShareBoardId(shareBoardId);
		sc.setComments(comments);
		sc.setAuthorId(userId);
		sc.setpCode(pCode);
		
		int result = new ShareService().insertComment(sc);
		
		if(result > 0) {
			response.sendRedirect("/share.do?shareId=" + shareBoardId);
		}else {
			response.sendRedirect("/views/share/error.jsp");
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
