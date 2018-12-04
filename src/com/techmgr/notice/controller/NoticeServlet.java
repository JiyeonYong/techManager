package com.techmgr.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techmgr.file.model.service.FileService;
import com.techmgr.file.model.vo.FileData;
import com.techmgr.notice.model.service.NoticeService;
import com.techmgr.notice.model.vo.Notice;
import com.techmgr.notice.model.vo.NoticeData;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet(name = "Notice", urlPatterns = { "/notice.do" })
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int id = Integer.parseInt(request.getParameter("noticeId"));
		
		NoticeData nd = new NoticeService().selectOneNotice(id);
		
		FileData fd = new FileService().selectOneFile(id);
		
		if(nd != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/notice/notice.jsp");
			request.setAttribute("fileData", fd);
			request.setAttribute("noticeData", nd);
			view.forward(request, response);
		} else {
			response.sendRedirect("views/notice/error.jsp");
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
