package com.techmgr.employee.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.techmgr.employee.model.vo.Employee;

public interface IEmployeeDao {
	public Employee selectOneEmployee(SqlSession session, String userId, String userPwd);
	public Employee checkId(SqlSession session,String userId);
	public int insertOneEmployee(SqlSession session, Employee employee);
}
