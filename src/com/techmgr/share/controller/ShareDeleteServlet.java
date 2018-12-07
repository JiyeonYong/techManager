package com.techmgr.share.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techmgr.share.model.service.ShareService;


/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet(name = "ShareDelete", urlPatterns = { "/shareDelete.do" })
public class ShareDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("shareId"));
		String authorId = request.getParameter("authorId");
		
		int result = new ShareService().deleteOneShare(id, authorId);
		
		if(result > 0) {
			response.sendRedirect("views/share/shareDeleteSuccess.jsp");
		}else {
			response.sendRedirect("/share.do?shareId="+id);
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
