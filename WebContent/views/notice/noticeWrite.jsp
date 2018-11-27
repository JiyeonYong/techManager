<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.techmgr.notice.model.vo.*"
    import = "com.techmgr.employee.model.vo.*"
    import = "java.util.ArrayList"%>
    
    <%
	
	session = request.getSession(false);
	
	Employee employee = (Employee)session.getAttribute("employee");
	
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 작성</title>
</head>
<body>
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendor/bootstrap/js/popper.min.js"></script>
	<script src="vender/bootstrap/js/bootstrap.min.js"></script>


	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<%@ include file = "/views/layout/slide_menu.jsp" %>
	<main role = "main" class = "col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	
		<center>
		<h6>공지사항 작성</h6>
		
		<div class ="card">
			<div class = "card-body">
				<div class="input-group mb-3">
    				<div class="input-group-prepend">
      					<span class="input-group-text">제목</span>
    				</div>
    				<input type="text" class="form-control" placeholder="제목을 입력해주세요 ">
  				</div>
			</div>
			
			<div class = "card-footer">
				<center>
					<input class = "btn btn-secondary btn-sm float-left" type = "button" value = "목록"/>
					<div class = "float-right">
					<input class = "btn btn-success btn-sm" type = "submit" value = "업로드"/>
					<input class = "btn btn-success btn-sm" type = "reset" value = "취소"/>
					</div>
				</center>
			</div>
		</div>
		
		
		</center>
	</main>
	
</body>
</html>