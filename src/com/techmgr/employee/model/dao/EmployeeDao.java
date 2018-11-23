package com.techmgr.employee.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

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



}