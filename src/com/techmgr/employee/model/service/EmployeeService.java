package com.techmgr.employee.model.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.techmgr.employee.model.dao.EmployeeDao;
import com.techmgr.employee.model.vo.Employee;

public class EmployeeService implements IEmployeeService {

	
	private SqlSession getSqlSession() {
		SqlSession session = null;
		String resource = "/mybatis-config.xml";
		
		try {
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			session = factory.openSession(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return session;
	}
	
	@Override
	public Employee selectOneEmployee(String userId, String userPwd) {
		SqlSession session = getSqlSession();
		
		Employee emp = new EmployeeDao().selectOneEmployee(session, userId, userPwd);
		session.close();
		
		return emp;
	}



}
