package com.techmgr.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techmgr.employee.model.service.EmployeeService;
import com.techmgr.employee.model.vo.Employee;

/**
 * Servlet implementation class ModifyUserServlet
 */
@WebServlet(name = "ModifyUser", urlPatterns = { "/modifyUser.do" })
public class ModifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		int result = new EmployeeService().updateUser(userId, userPwd, phone, email);
		
		Employee emp = new EmployeeService().selectOneEmployee(userId, userPwd);
		
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		session = request.getSession();
		session.setAttribute("employee", emp);
		
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
