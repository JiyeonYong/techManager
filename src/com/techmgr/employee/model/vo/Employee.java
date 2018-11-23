package com.techmgr.employee.model.vo;

import java.sql.Date;

public class Employee {
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private String email;
	private String pCode;
	private Date regDate;
	private String adminType;
	private String active;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//회원가입용 생성자
	public Employee(String userId, String userPwd, String userName, String phone, String email, String pCode) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.pCode = pCode;
	}

	public Employee(String userId, String userPwd, String userName, String phone, String email, String pCode,
			Date regDate, String adminType, String active) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.pCode = pCode;
		this.regDate = regDate;
		this.adminType = adminType;
		this.active = active;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getAdminType() {
		return adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
