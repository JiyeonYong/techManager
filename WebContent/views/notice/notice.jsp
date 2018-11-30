<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.techmgr.notice.model.vo.*"
    import = "com.techmgr.employee.model.vo.*"
    %>
    
<%
	Notice n = (Notice)request.getAttribute("notice");
	Employee emp = (Employee)session.getAttribute("employee");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항</title>
</head>
<body>
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="vendor/bootstrap/js/popper.min.js"></script>
	<script src="vender/bootstrap/js/bootstrap.min.js"></script>


	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file = "/views/layout/slide_menu.jsp" %>
	
	
	<main role = "main" class = "col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
	<center>
		<div class = "card">
			<div class="card-header">제목 : <%=n.getTitle()%></div>
  			<div class="card-body">Content</div> 
  			<div class="card-footer">Footer</div>
		</div>
		
		
	</center>
	</main>
</body>
</html>