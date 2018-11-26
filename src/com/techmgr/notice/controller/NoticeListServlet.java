package com.techmgr.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techmgr.notice.model.service.NoticeListService;
import com.techmgr.notice.model.vo.PageData;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet(name = "NoticeList", urlPatterns = { "/noticeList.do" })
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*int currentPage;
		
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		PageData pd = new NoticeListService().selectAllNotice(currentPage);
		
		if(pd != null) {
			RequestDispatcher view  = request.getRequestDispatcher("views/notice/noticeList.jsp");
			request.setAttribute("pageData", pd);
			view.forward(request, response);
		} else {
			response.sendRedirect("/views/notice/error.jsp");
		}*/
		
		int numPerPage = 10;
		int requestPage;
		
		if(request.getParameter("requestPage") == null) {
			requestPage = 1;
		}else {
			requestPage = Integer.parseInt(request.getParameter("requestPage"));
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
 