package com.techmgr.share.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techmgr.share.model.vo.PageData;
import com.techmgr.share.model.service.ShareService;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet(name = "ShareList", urlPatterns = { "/shareList.do" })
public class ShareListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage;
		
		if(request.getParameter("currentPage") == null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		PageData pd = new ShareService().selectAllShare(currentPage);
		
		if(pd != null) {
			RequestDispatcher view  = request.getRequestDispatcher("views/share/shareList.jsp");
			request.setAttribute("pageData", pd);
			view.forward(request, response);
		} else {
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
 