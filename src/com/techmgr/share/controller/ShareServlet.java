package com.techmgr.share.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techmgr.file.model.service.FileService;
import com.techmgr.file.model.vo.FileData;
import com.techmgr.share.model.service.ShareService;
import com.techmgr.share.model.vo.ShareData;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet(name = "Share", urlPatterns = { "/share.do" })
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int id = Integer.parseInt(request.getParameter("shareId"));
		
		ShareData sd = new ShareService().selectOneShare(id);
		
		FileData fd = new FileService().selectOneFile(id);
		
		if(sd != null) {
			RequestDispatcher view = request.getRequestDispatcher("views/share/share.jsp");
			request.setAttribute("fileData", fd);
			request.setAttribute("shareData", sd);
			view.forward(request, response);
		} else {
			response.sendRedirect("views/share/error.jsp");
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
