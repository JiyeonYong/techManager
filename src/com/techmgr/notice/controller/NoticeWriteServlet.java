package com.techmgr.notice.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.techmgr.employee.model.vo.Employee;
import com.techmgr.file.model.service.FileService;
import com.techmgr.file.model.vo.FileData;
import com.techmgr.notice.model.service.NoticeService;
import com.techmgr.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeWriteServlet
 */
@WebServlet(name = "NoticeWrite", urlPatterns = { "/noticeWrite.do" })
public class NoticeWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		
		String userId = ((Employee)session.getAttribute("employee")).getUserId();
		
		//첨부파일
		int fileSizeLimit = 5*1024*1024;
		String uploadPath = getServletContext().getRealPath("/") + "uploadFile" + "\\" + "notice";
		String encType = "UTF-8";
		
		MultipartRequest multi = new MultipartRequest(request, uploadPath, fileSizeLimit, encType, new DefaultFileRenamePolicy());
		
		String fileName = multi.getFilesystemName("uploadFile");
		System.out.println(fileName);
		String fullFilePath = uploadPath + "\\" + fileName;
		
		File file = new File(fullFilePath);
		long fileSize = file.length();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Timestamp uploadTime = null;
		
		uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
		
		FileData fd = null;
		
		if(fileName != null) {
			fd = new FileData(fileName, fullFilePath, fileSize, userId, uploadTime);
			new FileService().uploadFile(fd);
		}
		
		//글
		String title = multi.getParameter("title");
		String contents = multi.getParameter("contents");
		
			
		Notice notice = new Notice(title, contents, userId);
				
		
		int noticeInsertResult = new NoticeService().insertNotice(notice);
		
		if(noticeInsertResult > 0) {
			response.sendRedirect("/views/notice/writeSuccess.jsp");
		}else {
			response.sendRedirect("/views/notice/writeFail.jsp");
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
