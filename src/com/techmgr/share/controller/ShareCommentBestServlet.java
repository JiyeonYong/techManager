package com.techmgr.share.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techmgr.share.model.service.ShareService;

/**
 * Servlet implementation class ShareCommentBestServlet
 */
@WebServlet(name = "ShareCommentBest", urlPatterns = { "/shareCommentBest.do" })
public class ShareCommentBestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShareCommentBestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int shareId = Integer.parseInt(request.getParameter("shareId"));
		
		int result = new ShareService().updateBestComment(commentId, shareId);
		
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
