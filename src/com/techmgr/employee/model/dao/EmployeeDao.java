package com.techmgr.employee.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.techmgr.common.JDBCTemplate;
import com.techmgr.employee.model.vo.Employee;

public class EmployeeDao implements IEmployeeDao {

	@Override
	public Employee selectOneEmployee(SqlSession session, String userId, String userPwd) {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("userId", userId);
		parameters.put("userPwd", userPwd);
		
		Employee emp = session.selectOne("employee.selectOneEmployee", parameters);
		
		return emp;
		
	}

	@Override
	public Employee checkId(SqlSession session, String userId) {
		Employee emp = session.selectOne("employee.checkId", userId);
		return emp;
	}

	@Override
	public int insertOneEmployee(SqlSession session, Employee employee) {
		int result = session.insert("employee.insertOneEmployee", employee);
		System.out.println(result);
		return result;
	}

	public int updateUser(Connection conn, String userId, String userPwd, String phone, String email) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update users set user_pwd =?, phone=?, email=? where user_id=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userPwd);
			pstmt.setString(2, phone);
			pstmt.setString(3, email);
			pstmt.setString(4, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.Close(pstmt);
		}
		
		return result;
	}

	

}