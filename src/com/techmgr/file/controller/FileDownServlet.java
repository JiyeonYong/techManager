package com.techmgr.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techmgr.employee.model.vo.*;
import com.techmgr.file.model.service.FileService;
import com.techmgr.file.model.vo.FileData;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet(name = "FileDown", urlPatterns = { "/fileDown.do" })
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String fileName = request.getParameter("fileName");
		
		HttpSession session = request.getSession(false);

		String userId = ((Employee)session.getAttribute("employee")).getUserId();
		
		try {
			
			FileData fd = new FileService().fileDownload(fileName, userId);
			
			if(fd != null) { 
				File file = new File(fd.getFilePath());
				
				String encFileName = new String(fd.getFileName().getBytes(), "ISO-8859-1");
				
				response.setContentType("application/octat-stream");
				response.setContentLengthLong(file.length());
				response.setHeader("Content-Disposition", "attachment;filename=" + encFileName);
				
				FileInputStream fileIn = new FileInputStream(file);
				
				ServletOutputStream out = response.getOutputStream();
				
				byte[] output = new byte[4096];
				
				while(fileIn.read(output, 0, 4096) != -1) {
					out.write(output, 0, 4096);
				}
				
				fileIn.close();
				out.close();
				
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			response.sendRedirect("/views/error/error.jsp");
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
