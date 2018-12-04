package com.techmgr.employee.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.techmgr.common.JDBCTemplate;
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

	@Override
	public boolean checkId(String userId) {
		SqlSession session = getSqlSession();

		Employee emp = new EmployeeDao().checkId(session, userId);

		if (emp != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int insertOneEmployee(Employee employee) {
		SqlSession session = getSqlSession();

		int result = new EmployeeDao().insertOneEmployee(session, employee);

		if (result > 0) {
			session.commit();
		} else {
			session.rollback();
		}

		session.close();

		return result;
	}

	public int updateUser(String userId, String userPwd, String phone, String email) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new EmployeeDao().updateUser(conn, userId, userPwd, phone, email);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.Close(conn);
		
		return result;
	}

}
