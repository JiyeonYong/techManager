package com.techmgr.employee.model.service;

import com.techmgr.employee.model.vo.Employee;

public interface IEmployeeService {
	public Employee selectOneEmployee(String userId, String userPwd);
}
